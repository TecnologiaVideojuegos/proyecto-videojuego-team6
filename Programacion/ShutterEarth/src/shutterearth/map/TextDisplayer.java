/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shutterearth.map;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.command.BasicCommand;
import org.newdawn.slick.command.Command;
import org.newdawn.slick.command.InputProvider;
import org.newdawn.slick.command.InputProviderListener;
import org.newdawn.slick.command.KeyControl;
import org.newdawn.slick.gui.TextField;
import shutterearth.Game;
import shutterearth.Media;
import shutterearth.screens.Scene;

/**
 *
 * @author mr.blissfulgrin
 */
public class TextDisplayer extends Scene implements InputProviderListener
{    
    private InputProvider provider;
    private final Command next;
    private final Field field;
    
    private TextField txt;
    private int step;
    private String toShow;
    private boolean nextable;
    private final boolean [] done;
    private final int doNext;
    
    public TextDisplayer (Field field, int stage, int doNext)
    {
        field.pause();
        this.field = field;
        next = new BasicCommand("click");
        
        this.doNext = doNext;
        
        this.toShow = "";
        this.step = (stage -1)*10;
        this.nextable = true;
        done = new boolean [10]; 
        for (int j = 0; j < done.length; j++)
        {
            done [j] = false;
        }
    }
    
    @Override
    public void Render(GameContainer gc, Graphics g) throws SlickException
    {
        Game.getMedia().getImage(Media.IMAGE.GREY).draw(0,0,Game.getX(),Game.getY());
        txt.render(gc, g);
    }

    @Override
    public void Update(GameContainer gc, float t) throws SlickException
    {
        switch (step)
        {
            case 0:
                if (!done[step%10])
                {
                    toShow += "Hola, bienvenido \n-- Pulsa ENTER para continuar\n";
                    done[step%10] = true;
                }
                break;
            case 1:
                if (!done[step%10])
                {
                    toShow += "SEGUNDO MMSS \n-- Pulsa ENTER para continuar\n";
                    done[step%10] = true;
                }
                break;
            case 2:
                if (!done[step%10])
                {
                    toShow += "ULTIMO MMSS \n-- Pulsa ENTER para salir\n";
                    done[step%10] = true;
                }
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                nextable = false;
                break;
            case 10:
                if (!done[step%10])
                {
                    toShow += "";
                    done[step%10] = true;
                }
                break;
            case 11:
                if (!done[step%10])
                {
                    toShow += "";
                    done[step%10] = true;
                }
                break;
            case 12:
                if (!done[step%10])
                {
                    toShow += "";
                    done[step%10] = true;
                }
                break;
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
                nextable = false;
                break;
            case 20:
                if (!done[step%10])
                {
                    toShow += "";
                    done[step%10] = true;
                }
                break;
            case 21:
                if (!done[step%10])
                {
                    toShow += "";
                    done[step%10] = true;
                }
                break;
            case 22:
                if (!done[step%10])
                {
                    toShow += "";
                    done[step%10] = true;
                }
                break;
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
                nextable = false;
                break;
            case 30:
                if (!done[step%10])
                {
                    toShow += "";
                    done[step%10] = true;
                }
                break;
            case 31:
                if (!done[step%10])
                {
                    toShow += "";
                    done[step%10] = true;
                }
                break;
            case 32:
                if (!done[step%10])
                {
                    toShow += "";
                    done[step%10] = true;
                }
                break;
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
                nextable = false;
                break;
            case 40:
                if (!done[step%10])
                {
                    toShow += "";
                    done[step%10] = true;
                }
                break;
            case 41:
                if (!done[step%10])
                {
                    toShow += "";
                    done[step%10] = true;
                }
                break;
            case 42:
                if (!done[step%10])
                {
                    toShow += "";
                    done[step%10] = true;
                }
                break;
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
                nextable = false;
                break;
            case 50:
                if (!done[step%10])
                {
                    toShow += "";
                    done[step%10] = true;
                }
                break;
            case 51:
                if (!done[step%10])
                {
                    toShow += "";
                    done[step%10] = true;
                }
                break;
            case 52:
                if (!done[step%10])
                {
                    toShow += "";
                    done[step%10] = true;
                }
                break;
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
            case 59:
                nextable = false;
                break;
            case 60:
                if (!done[step%10])
                {
                    toShow += "";
                    done[step%10] = true;
                }
                break;
            case 61:
                if (!done[step%10])
                {
                    toShow += "";
                    done[step%10] = true;
                }
                break;
            case 62:
                if (!done[step%10])
                {
                    toShow += "";
                    done[step%10] = true;
                }
                break;
            case 63:
            case 64:
            case 65:
            case 66:
            case 67:
            case 68:
            case 69:
                nextable = false;
                break;
            case 70:
                if (!done[step%10])
                {
                    toShow += "";
                    done[step%10] = true;
                }
                break;
            case 71:
                if (!done[step%10])
                {
                    toShow += "";
                    done[step%10] = true;
                }
                break;
            case 72:
                if (!done[step%10])
                {
                    toShow += "";
                    done[step%10] = true;
                }
                break;
            case 73:
            case 74:
            case 75:
            case 76:
            case 77:
            case 78:
            case 79:
                nextable = false;
                break;
            case 80:
                if (!done[step%10])
                {
                    toShow += "";
                    done[step%10] = true;
                }
                break;
            case 81:
                if (!done[step%10])
                {
                    toShow += "";
                    done[step%10] = true;
                }
                break;
            case 82:
                if (!done[step%10])
                {
                    toShow += "";
                    done[step%10] = true;
                }
                break;
            case 83:
            case 84:
            case 85:
            case 86:
            case 87:
            case 88:
            case 89:
                nextable = false;
                break;
            case 90:
                if (!done[step%10])
                {
                    toShow += "";
                    done[step%10] = true;
                }
                break;
            case 91:
                if (!done[step%10])
                {
                    toShow += "";
                    done[step%10] = true;
                }
                break;
            case 92:
                if (!done[step%10])
                {
                    toShow += "";
                    done[step%10] = true;
                }
                break;
            case 93:
            case 94:
            case 95:
            case 96:
            case 97:
            case 98:
            case 99:
                nextable = false;
                break;
            case 100:
                if (!done[step%10])
                {
                    toShow += "-- \tAhora nunca podrás recuperar a tu hija\n";
                    done[step%10] = true;
                }
                break;
            case 101:
                if (!done[step%10])
                {
                    toShow += "Diana: \tpienso volver a por ti,\n\tLa humanidad no descansará hasta haber acabado contigo\n";
                    done[step%10] = true;
                }
                break;
            case 102:
                if (!done[step%10])
                {
                    toShow += "-- \tNunca podréis vencerme\n";
                    done[step%10] = true;
                }
                break;
            case 103:
                if (!done[step%10])
                {
                    toShow += "-- \tDile adiós a tu hija\n";
                    done[step%10] = true;
                }
            case 104:
            case 105:
            case 106:
            case 107:
            case 108:
            case 109:
                nextable = false;
                break;
        }
        txt.setText(toShow);
    }

    @Override
    public void init(GameContainer gc) throws SlickException
    {
        Game.getMedia().getMusic(Media.MUSIC.CANCION_FONDO).loop();
        provider = new InputProvider(gc.getInput());
        provider.addListener(this);
        provider.bindCommand(new KeyControl(Input.KEY_ENTER), next);
        
        this.txt = new TextField(gc, gc.getDefaultFont(), Game.getX()/2-Game.getX()/4, Game.getY()/2 - Game.getY()/4, Game.getX()/2, Game.getY()/2);
        txt.setBackgroundColor(Color.gray);
        txt.setBorderColor(Color.black);
        txt.setTextColor(Color.white);
        txt.setAcceptingInput(false);
    }

    @Override
    public void controlPressed(Command cmnd)
    {
        if (cmnd.equals(next))
        {
            if (nextable)
            {
                step ++;
            }
            else
            {
                field.wake();
                if (doNext == 0)
                {
                    field.startAnimation();
                }
                else if (doNext == 1)
                {
                    field.startBattle();
                }
                Game.removeSence(this);
                provider.clearCommand(next);
            }
        }
    }

    @Override
    public void controlReleased(Command cmnd){}
    
    @Override
    public String toString()
    {
        return "TextDisplayer "+this.step;
    }
}
