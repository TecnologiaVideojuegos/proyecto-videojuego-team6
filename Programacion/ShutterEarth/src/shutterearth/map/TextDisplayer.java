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
import org.newdawn.slick.command.Control;
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
    private final Control enter = new KeyControl(Input.KEY_ENTER);
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
        this.step = stage*10;
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
        g.setColor(Color.yellow);
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
                    toShow += "(Hit ENTER to continue reading...)\n";
                    done[step%10] = true;
                }
            case 1:
                if (!done[step%10])
                {
                    toShow += "(In game hit BACK to PAUSE...)\n";
                    done[step%10] = true;
                }
            case 2:
                if (!done[step%10])
                {
                    toShow += "Diana: ...\n";
                    done[step%10] = true;
                }
                break;
            case 3:
                if (!done[step%10])
                {
                    toShow += "Diana: Esos malditos aliens\n";
                    done[step%10] = true;
                }
                break;
            case 4:
                if (!done[step%10])
                {
                    toShow += "Diana: No les bastaba con destrozar el planeta\n"
                            + "       tenían tambén que raptar a mi hija...\n";
                    done[step%10] = true;
                }
                break;
            case 5:
                if (!done[step%10])
                {
                    toShow += "Diana: Voy a encontrarla cueste lo que cueste\n";
                    done[step%10] = true;
                }
            case 6:
            case 7:
            case 8:
            case 9:
                nextable = false;
                break;
            case 10:
                if (!done[step%10])
                {
                    toShow += "Diana: He visto como se llevaban a mi hija por aquí\n";
                    done[step%10] = true;
                }
                break;
            case 11:
                if (!done[step%10])
                {
                    toShow += "Diana: Utilizan los antiguos edificios humanos para esconderse\n";
                    done[step%10] = true;
                }
                break;
            case 12:
                if (!done[step%10])
                {
                    toShow += "Diana: Por fin tanto entrenamiento militar está sirviendo para algo\n";
                    done[step%10] = true;
                }
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
                    toShow += "Diana: Ese último edificio estaba plagado de aliens pero este parece\n"
                            + "       estar vacío, con un poco de suerte encontraré a mi hija y\n"
                            + "       acabará toda esta pesadilla.\n";
                    done[step%10] = true;
                }
                break;
            case 21:
                if (!done[step%10])
                {
                    toShow += "Diana: Se escuchan unos extraños ruidos de fondo qué podrán ser\n";
                    done[step%10] = true;
                }
                break;
            case 22:
                if (!done[step%10])
                {
                    toShow += "Diana: Esto está demasiado tranquilo...\n";
                    done[step%10] = true;
                }
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
                    toShow += "Diana: Que horrible, están llenando las calles con esos trastos voladores\n";
                    done[step%10] = true;
                }
                break;
            case 31:
                if (!done[step%10])
                {
                    toShow += "Diana: Por suerte he logrado destruirlos todos\n";
                    done[step%10] = true;
                }
                break;
            case 32:
                if (!done[step%10])
                {
                    toShow += "Diana: Ahora nada me impedirá recupera a mi hija\n";
                    done[step%10] = true;
                }
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
                    toShow += "Diana: Parece que su tecnología es cada vez más mortal\n";
                    done[step%10] = true;
                }
                break;
            case 41:
                if (!done[step%10])
                {
                    toShow += "Diana: Pero yo estoy cada vez más cerca de mi hija\n";
                    done[step%10] = true;
                }
                break;
            case 42:
                if (!done[step%10])
                {
                    toShow += "Diana: Eso es todo lo que importa\n";
                    done[step%10] = true;
                }
                break;
            case 43:
                if (!done[step%10])
                {
                    toShow += "Diana: El mundo estrá en llamas pero nosotras estaremos juntas\n";
                    done[step%10] = true;
                }
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
                    toShow += "Diana: Me la ha vuelto a jugar...\n";
                    done[step%10] = true;
                }
                break;
            case 51:
                if (!done[step%10])
                {
                    toShow += "Diana: ese Alien acabará pagando todo lo que me está haciendo sufrir\n";
                    done[step%10] = true;
                }
                break;
            case 52:
                if (!done[step%10])
                {
                    toShow += "Diana: Pero mi hija está cerca, lo presiento\n";
                    done[step%10] = true;
                }
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
                    toShow += "Diana: Cada vez hay más navecitas de esas...\n";
                    done[step%10] = true;
                }
                break;
            case 61:
                if (!done[step%10])
                {
                    toShow += "Diana: Solo quiero volver a abrazar a mi hija\n";
                    done[step%10] = true;
                }
                break;
            case 62:
                if (!done[step%10])
                {
                    toShow += "Diana: Esclavizar a la humanidad entera es una cosa pero\n"
                            + "       el dolor de una madre...\n";
                    done[step%10] = true;
                }
                break;
            case 63:
                if (!done[step%10])
                {
                    toShow += "Diana: ESO YA ES PASARSE!!!\n";
                    done[step%10] = true;
                }
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
                    toShow += "Diana: He visto al Alien entrar en este edificio\n";
                    done[step%10] = true;
                }
                break;
            case 71:
                if (!done[step%10])
                {
                    toShow += "Diana: Puede que esta tragedia esté llegando a su fin\n";
                    done[step%10] = true;
                }
                break;
            case 72:
                if (!done[step%10])
                {
                    toShow += "Diana: Solo de pensar en volver a estar con mi hija\n";
                    done[step%10] = true;
                }
                break;
            case 73:
                if (!done[step%10])
                {
                    toShow += "Diana: Aunque me dará pena esto de dejar de patear culos Alien\n";
                    done[step%10] = true;
                }
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
                    toShow += "Diana: Casi no salgo de esa, pero yo por mi hija MA-TO \n";
                    done[step%10] = true;
                }
                break;
            case 81:
                if (!done[step%10])
                {
                    toShow += "Diana: Se oye demasiado ruido, deben de estar sacando\n"
                            + "       la maquinaria pesada\n";
                    done[step%10] = true;
                }
                break;
            case 82:
                if (!done[step%10])
                {
                    toShow += "Diana: Pero no hay nada que pueda con el amor de una madre\n";
                    done[step%10] = true;
                }
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
                    toShow += "Diana: Ese escurridizo Alien acaba de entrar aquí\n";
                    done[step%10] = true;
                }
                break;
            case 91:
                if (!done[step%10])
                {
                    toShow += "Diana: Pero no va a salir\n";
                    done[step%10] = true;
                }
                break;
            case 92:
                if (!done[step%10])
                {
                    toShow += "Diana: Al menos no con vida\n";
                    done[step%10] = true;
                }
                break;
            case 93:
                if (!done[step%10])
                {
                    toShow += "Diana: HIJAAAA, HE VENIDO A SALVARTE!!!\n";
                    done[step%10] = true;
                }
                break;
            case 94:
                if (!done[step%10])
                {
                    toShow += "Diana: He estado tan ocupada salvándola que no me ha\n"
                            + "       tanto tiempo ni a ponerla nombre\n";
                    done[step%10] = true;
                }
                break;
            case 95:
                if (!done[step%10])
                {
                    toShow += "Diana: Menudo rollo que son los críos\n";
                    done[step%10] = true;
                }
            case 96:
            case 97:
            case 98:
            case 99:
                nextable = false;
                break;
            case 100:
                if (!done[step%10])
                {
                    toShow += "-----: Ahora nunca podrás recuperar a tu hija\n";
                    done[step%10] = true;
                }
                break;
            case 101:
                if (!done[step%10])
                {
                    toShow += "Diana: Pienso volver a por ti, La humanidad no descansará hasta haber acabado contigo\n";
                    done[step%10] = true;
                }
                break;
            case 102:
                if (!done[step%10])
                {
                    toShow += "-----: \tNunca podréis vencerme\n";
                    done[step%10] = true;
                }
                break;
            case 103:
                if (!done[step%10])
                {
                    toShow += "-----: \tDile adiós a tu hija\n";
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
            case 110:
                if (!done[step%10])
                {
                    toShow += "Diana: MI HIJAAAA!!!!\n";
                    done[step%10] = true;
                }
                break;
            case 111:
                if (!done[step%10])
                {
                    toShow += "Diana: Todo lo que he sufrido por recuperarla...\n";
                    done[step%10] = true;
                }
                break;
            case 112:
                if (!done[step%10])
                {
                    toShow += "Diana: El esfuerzo por fin ha merecido la pena\n"
                            + "       juntas de nuevo, que bonito final\n";
                    done[step%10] = true;
                }
            case 113:
            case 114:
            case 115:
            case 116:
            case 117:
            case 118:
            case 119:
                nextable = false;
                break;
            case 120:
                if (!done[step%10])
                {
                    toShow += "Diana: Mi hija, DÓNDE ESTÁ!?!?!?!\n";
                    done[step%10] = true;
                }
                break;
            case 121:
                if (!done[step%10])
                {
                    toShow += "Diana: HA DESAPARECIDO!!!!!\n";
                    done[step%10] = true;
                }
                break;
            case 122:
                if (!done[step%10])
                {
                    toShow += "Diana: Ese!!!! es el alien que se la llevó\n";
                    done[step%10] = true;
                }
                break;
            case 123:
                if (!done[step%10])
                {
                    toShow += "Diana: Esto no va a quedar así\n";
                    done[step%10] = true;
                }
            case 124:
            case 125:
            case 126:
            case 127:
            case 128:
            case 129:
                nextable = false;
                break;
            case 130:
                if (!done[step%10])
                {
                    toShow += "Diana: Por fin, esta vez de verdad, junto a mi hija de nuevo\n";
                    done[step%10] = true;
                }
                break;
            case 131:
                if (!done[step%10])
                {
                    toShow += "Diana: Te he echado tanto de menos pequeña\n";
                    done[step%10] = true;
                }
            case 132:
            case 133:
            case 134:
            case 135:
            case 136:
            case 137:
            case 138:
            case 139:
                nextable = false;
                break;
            case 140:
                if (!done[step%10])
                {
                    toShow += "Diana: SERÁ UNA BROMA!!!!\n";
                    done[step%10] = true;
                }
                break;
            case 141:
                if (!done[step%10])
                {
                    toShow += "Diana: Tú de nuevo\n";
                    done[step%10] = true;
                }
                break;
            case 142:
                if (!done[step%10])
                {
                    toShow += "-----: Me derrotaste una vez pero solo una\n";
                    done[step%10] = true;
                }
                break;
            case 143:
                if (!done[step%10])
                {
                    toShow += "-----: Vas a aprender a hacer las cosas a lo Alien\n";
                    done[step%10] = true;
                }
            case 144:
            case 145:
            case 146:
            case 147:
            case 148:
            case 149:
                nextable = false;
                break;
            case 150:
                if (!done[step%10])
                {
                    toShow += "-----: Tú de nuevo?\n";
                    done[step%10] = true;
                }
                break;
            case 151:
                if (!done[step%10])
                {
                    toShow += "-----: Pensaba que ya te babrías rendido\n";
                    done[step%10] = true;
                }
                break;
            case 152:
                if (!done[step%10])
                {
                    toShow += "-----: En fin, si quieres morir junto a tu hija me parece bien\n";
                    done[step%10] = true;
                }
                break;
            case 153:
                if (!done[step%10])
                {
                    toShow += "Diana: Vas a morder el polvo\n";
                    done[step%10] = true;
                }
            case 154:
            case 155:
            case 156:
            case 157:
            case 158:
            case 159:
                nextable = false;
                break;
            case 160:
                if (!done[step%10])
                {
                    toShow += "------------------Shutter Earth-------------------------\n";
                    done[step%10] = true;
                }
                break;
            case 161:
                if (!done[step%10])
                {
                    toShow += "----------------------Team 6-----------------------------\n";
                    done[step%10] = true;
                }
                break;
            case 162:
                if (!done[step%10])
                {
                    toShow += "--------------Juan Casado Ballesteros-------------------\n";
                    done[step%10] = true;
                }
                break;
            case 163:
                if (!done[step%10])
                {
                    toShow += "---------------Daniel Fernández Diaz--------------------\n";
                    done[step%10] = true;
                }
                break;
            case 164:
                if (!done[step%10])
                {
                    toShow += "----------------Jorge  Garcia Garcia--------------------\n";
                    done[step%10] = true;
                }
                break;
            case 165:
                if (!done[step%10])
                {
                    toShow += "-----------------Pablo Pardo García---------------------\n";
                    done[step%10] = true;
                }
                break;
            case 166:
            case 167:
            case 168:
            case 169:
                nextable = false;
                break;
            case 170:
                if (!done[step%10])
                {
                    toShow += "Diana: Por fin junats\n";
                    done[step%10] = true;
                }
                break;
            case 171:
                if (!done[step%10])
                {
                    toShow += "<3 <3 <3 <3 <3 <3 <3 <3 <3 <3 <3 <3 <3 <3 <3 <3 <3 <3 <3 <3 <3 <3 <3 \n";
                    done[step%10] = true;
                }
                break;
            case 172:
                if (!done[step%10])
                {
                    toShow += "Diana: Volvamos a casa, nuestra historia no ha hecho más que empezar\n";
                    done[step%10] = true;
                }
            case 173:
            case 174:
            case 175:
            case 176:
            case 177:
            case 178:
            case 179:
        }
        txt.setText(toShow);
    }

    @Override
    public void init(GameContainer gc) throws SlickException
    {
        if (doNext == 4)
        {
           Game.getMedia().getMusic(Media.MUSIC.END_SONG).loop(); 
        }
        else
        {
            Game.getMedia().getMusic(Media.MUSIC.CANCION_FONDO).loop();
        }
        provider = new InputProvider(gc.getInput());
        provider.addListener(this);
        provider.bindCommand(enter, next);
        
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
                switch (doNext)
                {
                    case 0:
                        field.startAnimation();
                        break;
                    case 1:
                        field.startBattle();
                        break;
                    case 4:
                        field.exit();
                        break;
                    default:
                        break;
                }
                Game.removeSence(this);
                provider.clearCommand(next);
                provider.unbindCommand(enter);
                provider.setActive(false);
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
