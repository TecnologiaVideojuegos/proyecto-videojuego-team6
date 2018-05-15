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
                    {5,10,40,0},      //Arma Minima
                    {7,10,40,0},
                    {9,10,40,0},
                    {10,5,35,0},
                    {10,5,35,0},
                },
                {
                    {0,0,0,0},
                    {10,15,50,8},     //Arma Base
                    {12,15,50,8},
                    {13,15,50,9},
                    {14,15,50,9},
                    {15,15,50,9},
                },
                {
                    {0,0,0,0},
                    {40,30,80,30},   //Arma Fuerte
                    {43,30,80,30},
                    {45,30,80,30},
                    {50,30,80,35},
                    {50,30,80,35},
                },
                {
                    {0,0,0,0},
                    {30,5,30,20},    //Arma Rápida
                    {32,5,30,20},
                    {33,0,30,20},
                    {34,0,30,8},
                    {35,0,30,8},
                },
                {
                    {0,0,0,0},
                    {60,20,50,40},   //Arma Final
                    {65,20,50,45},
                    {70,25,50,50},
                    {70,25,50,55},
                    {70,20,50,60},
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
                    {31,140,600,0},   //Nave Fuerte
                    {33,140,595,0},
                    {35,140,590,0},
                    {37,140,585,0},
                    {40,140,580,0},
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
