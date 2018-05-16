/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shutterearth.characters;

/**
 *
 * @author mr.blissfulgrin
 */
public class Gun
{
    private final int damage;
    private final int delay;
    private final int speed;
    private final int consume;
    private final int id;
    private final int level;
    
    public Gun (int id, int level, int enemy)
    {
        this.id = id;
        this.level = level;
        int [][][][] gun = new int [][][][]
        {
            {
                {
                    {0,0,0,0},
                    {5,0,75,0},      //Arma Minima
                    {7,0,75,0},
                    {9,0,75,0},
                    {10,0,70,0},
                    {11,0,70,0},
                },
                {
                    {0,0,0,0},
                    {10,0,65,10},     //Arma Base
                    {12,0,65,10},
                    {14,0,60,10},
                    {16,0,60,9},
                    {18,0,60,9},
                },
                {
                    {0,0,0,0},
                    {50,0,110,65},   //Arma Fuerte
                    {53,0,110,65},
                    {55,0,105,65},
                    {60,0,100,60},
                    {60,0,100,60},
                },
                {
                    {0,0,0,0},
                    {30,0,30,30},    //Arma Rápida
                    {32,0,30,30},
                    {33,0,25,30},
                    {34,0,25,30},
                    {35,0,20,30},
                },
                {
                    {0,0,0,0},
                    {70,00,60,80},   //Arma Final
                    {70,00,55,80},
                    {75,0,50,80},
                    {75,0,45,75},
                    {80,0,40,70},
                },
            },
            {
                {
                    {5,90,200,0},    //Enemigo Base
                    {7,90,200,0},
                    {8,85,200,0},
                    {10,85,195,0},
                    {12,85,190,0},
                },
                {
                    {10,100,300,0},    //Enemigo Fuerte
                    {12,100,300,0},
                    {14,100,295,0},
                    {17,95,295,0},
                    {20,90,290,0},
                },
                {
                    {21,120,400,0},   //Nave Base
                    {23,120,395,0},
                    {25,120,395,0},
                    {27,120,385,0},
                    {30,120,380,0},
                },
                {
                    {31,140,700,0},   //Nave Fuerte
                    {33,140,695,0},
                    {35,140,690,0},
                    {37,140,685,0},
                    {40,140,680,0},
                },
                {
                    {10,80,100,0},   //Enemigo Final
                    {15,80,100,0},
                    {20,80,100,0},
                    {25,80,100,0},
                    {30,80,100,0},
                },
            }
        };
        damage = gun [enemy][id][level][0];
        delay = gun [enemy][id][level][1]; //Tiempo en aparecer la bala
        speed = gun [enemy][id][level][2]; //Tiempo en poder volver a disparar
        consume = gun [enemy][id][level][3];
        /*
        System.out.println("ID: "+id);
        System.out.println("Level: "+level);
        System.out.println("Damage: "+damage);
        System.out.println("Speed: "+speed);
        System.out.println("Consume: "+consume);*/
    }
    
    public int getDamage()
    {
        return damage;
    }
    public int getSpeed()
    {
        return speed;
    }
    public int getDelay()
    {
        return delay;
    }
    public int getConsume()
    {
        return consume;
    }
    public int getID()
    {
        return id;
    }
    public int getLevel()
    {
        return level;
    }
}
