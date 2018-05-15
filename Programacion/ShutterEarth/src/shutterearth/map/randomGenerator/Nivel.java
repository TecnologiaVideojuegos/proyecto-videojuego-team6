package shutterearth.map.randomGenerator;

import java.util.ArrayList;
import javafx.util.Pair;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import shutterearth.Game;

public class Nivel 
{
    private ArrayList<Habitacion> nivel;
    private final Habitacion heroSpawn;
    
    private final AppGameContainer g;
    private final int gW;
    private final int gH;
    
    public Nivel(AppGameContainer g) throws SlickException
    {
        this.g = g;
        this.gW = Game.getX();
        this.gH = Game.getY();
        
        generacion();
        heroSpawn = nivel.get(0);
        nivel.sort(null);
        paredes();
    }
    
    public ArrayList<Habitacion> getNv()
    {
        return nivel;
    }
    
    public Habitacion getHeroSpawn()
    {
        return heroSpawn;
    }
    
    public ArrayList<Rectangle> getBulleytLimits()
    {
        ArrayList<Rectangle> aux = new ArrayList<>();
        nivel.forEach((h) ->
        {
            aux.addAll(h.getBulletLimits());
        });
        return aux;
    }
    
    public ArrayList<Pair> getSpawns(int diffFactor)
    {
        ArrayList<Pair> aux = new ArrayList<>();
        int factor;
        for(Habitacion h : nivel)
        {
            if(!h.equals(heroSpawn))
            {
                factor = ((int)(Math.random()*100))%diffFactor;
                aux.addAll(h.spawn(((int)(Math.random()*100))%factor));
            }
        }
        return aux;
    }
    
    public void render(Graphics g)
    {
        nivel.forEach((h) ->
        {
            h.render(g);
        });
    }
    
    private void resetCeldas(Celda[][] celdas, int[] filasCount) throws SlickException
    {
        for(int i=0;i<celdas.length;i++)
            for(int j=0;j<celdas[i].length;j++)
                celdas[i][j] = new Celda(((i%8)*Prop.ceWI*gW)+Prop.ceWI*gW,((j%8)*(Prop.ceTHIRDH*gH*3))+Prop.hubH*gH,Prop.ceWI*gW,Prop.ceTHIRDH*gH*3);
        for(int i=0;i<filasCount.length;i++) filasCount[i] = celdas.length;
    }
    
    private String genId(int id)
    {
        id++;
        return ""+id;
    }
    
    private void generacion() throws SlickException
    {
        int cellCount=1, cellNum = 32;
        int r=0, c=7;
        int rand;
        int id=0;
        
        Celda[][] celdas = new Celda[8][8];
        int[] filasCount = new int[8];
        resetCeldas(celdas, filasCount);
        
        nivel = new ArrayList<>();//reset del nivel
        
        celdas[r][c].setVisited(true);
        Habitacion hab = new Habitacion(g,celdas[r][c],genId(id));
        celdas[r][c].setHab(hab);
        this.nivel.add(hab);
        filasCount[c]--;
        
        do
        {
            if((celdas[r][c].getHab().getCount()<3)&&(filasCount[c]>0)) rand = ((int)(Math.random()*400))%2;//restringido a izquierda o derecha
            else if(celdas[r][c].getHab().getCount()==4) rand = (((int)(Math.random()*400))%2)+2;//restringido a arriba o abajo
            else rand = ((int)(Math.random()*400))%4;
            try
            {
                switch(rand)
                {
                    case 0:
                        if(!celdas[r+1][c].isVisited())
                        {//derecha
                            cellCount++;
                            celdas[r+1][c].setVisited(true);
                            celdas[r+1][c].setHab(celdas[r][c].getHab());
                            celdas[r][c].getHab().addCelda(celdas[r+1][c]);
                            filasCount[c]--;
                        }
                        r = r+1;
                        break;
                    case 1:
                        if(!celdas[r-1][c].isVisited())
                        {//izquierda
                            cellCount++;
                            celdas[r-1][c].setVisited(true);
                            celdas[r-1][c].setHab(celdas[r][c].getHab());
                            celdas[r][c].getHab().addCelda(celdas[r-1][c]);
                            filasCount[c]--;
                        }
                        r = r-1;
                        break;
                    case 2:
                        if(!((!celdas[r][c-1].isVisited())&&(filasCount[c-1]<=1)))
                        {
                            if(!celdas[r][c-1].isVisited())
                            {//arriba
                                cellCount++;
                                celdas[r][c-1].setVisited(true);
                                hab = new Habitacion(g, celdas[r][c-1],genId(id));
                                nivel.add(hab);
                                celdas[r][c-1].setHab(hab);
                                celdas[r][c].getHab().addSalidaSup(celdas[r][c], celdas[r][c-1].getHab());
                                celdas[r][c-1].getHab().addSalidaInf(celdas[r][c-1], celdas[r][c].getHab());
                                filasCount[c-1]--;
                            }
                            c = c-1;
                        }
                        break;
                    case 3:
                        if(!((!celdas[r][c+1].isVisited())&&(filasCount[c+1]<=1)))
                        {
                            if(!celdas[r][c+1].isVisited())
                            {//abajo
                                cellCount++;
                                celdas[r][c+1].setVisited(true);
                                hab = new Habitacion(g, celdas[r][c+1],genId(id));
                                nivel.add(hab);
                                celdas[r][c+1].setHab(hab);
                                celdas[r][c].getHab().addSalidaInf(celdas[r][c], celdas[r][c+1].getHab());
                                celdas[r][c+1].getHab().addSalidaSup(celdas[r][c+1],celdas[r][c].getHab());
                                filasCount[c+1]--;
                            }
                            c = c+1;
                        }
                        break;
                }
            } 
            catch (IndexOutOfBoundsException e){}//Captura el IndexOutOfBoundsException y lo vuelve a intentar
        }
        while((cellCount<cellNum)||(celdas[r][c].getHab().getCount()==1));
        
        //Eliminamos las habitaciones de una celda
        int aux = 2;
        for(int i=0;i<celdas.length;i++)
        {
            for(int j=0;j<celdas[i].length;j++)
            {
                if((celdas[i][j].isVisited())&&(celdas[i][j].getHab().getCount()==1))
                {
                    try
                    { 
                        if(celdas[i-1][j].getHab().getCount()<=2) aux = 1;
                    }
                    catch(Exception e)
                    { 
                        aux = 3;
                    }
                    try
                    { 
                        if(celdas[i+1][j].getHab().getCount()<=2) aux = 0;
                    }
                    catch(Exception e)
                    { 
                        aux = 4;
                    }
                    switch(aux)
                    {
                        case 2:
                            aux = ((int)(Math.random()*32))%2;
                            break;
                        case 3:
                            aux = 0;
                            break;
                        case 4:
                            aux = 1;
                            break;
                    }

                    switch(aux)
                    {
                        case 0:
                            //revisa la celda derecha
                            nivel.remove(celdas[i][j].getHab());
                            celdas[i+1][j].getHab().addCelda(celdas[i][j]);
                            for(Salida s : celdas[i][j].getHab().getSalSup())
                            {
                                celdas[i+1][j].getHab().addSalidaSup(s);
                                for(Salida y :s.getNext().getSalInf())
                                    if(y.getNext().equals(celdas[i][j].getHab()))
                                        y.setHab(celdas[i+1][j].getHab());
                            }
                            for(Salida s : celdas[i][j].getHab().getSalInf())
                            {
                                celdas[i+1][j].getHab().addSalidaInf(s);
                                for(Salida y :s.getNext().getSalSup())
                                    if(y.getNext().equals(celdas[i][j].getHab()))
                                        y.setHab(celdas[i+1][j].getHab());
                            }
                            celdas[i][j].setHab(celdas[i+1][j].getHab());
                            break;
                        case 1:
                            //revisa la celda izquierda
                            nivel.remove(celdas[i][j].getHab());
                            celdas[i-1][j].getHab().addCelda(celdas[i][j]);
                            for(Salida s : celdas[i][j].getHab().getSalSup())
                            {
                                celdas[i-1][j].getHab().addSalidaSup(s);
                                for(Salida y :s.getNext().getSalInf())
                                    if(y.getNext().equals(celdas[i][j].getHab()))
                                        y.setHab(celdas[i-1][j].getHab());
                            }
                            for(Salida s : celdas[i][j].getHab().getSalInf())
                            {
                                celdas[i-1][j].getHab().addSalidaInf(s);
                                for(Salida y :s.getNext().getSalSup())
                                    if(y.getNext().equals(celdas[i][j].getHab()))
                                        y.setHab(celdas[i-1][j].getHab());
                            }
                            celdas[i][j].setHab(celdas[i-1][j].getHab());
                            break;
                    }
                }
            }
        }
    }
    
    private void paredes()
    {
        for(int i=0;i<nivel.size();i++)
        {
            if(i!=0)
                if(nivel.get(i-1).getY()==nivel.get(i).getY())
                    nivel.get(i).addBulletLimits(nivel.get(i).getX());
            if(i<(nivel.size()-1))
                if(nivel.get(i+1).getY()==nivel.get(i).getY())
                    nivel.get(i).addBulletLimits(nivel.get(i).getMaxX());
        }
    }
}
