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
                    {5,40,40,0},      //Arma Minima
                    {7,40,40,0},
                    {9,40,40,0},
                    {10,35,35,0},
                    {10,35,35,0},
                },
                {
                    {0,0,0,0},
                    {10,40,50,8},     //Arma Base
                    {12,40,50,8},
                    {13,40,50,9},
                    {14,40,50,9},
                    {15,40,50,9},
                },
                {
                    {0,0,0,0},
                    {40,60,80,30},   //Arma Fuerte
                    {43,60,80,30},
                    {45,60,80,30},
                    {50,60,80,35},
                    {50,60,80,35},
                },
                {
                    {0,0,0,0},
                    {30,40,30,20},    //Arma Rápida
                    {32,35,30,20},
                    {33,30,30,20},
                    {34,30,30,8},
                    {35,25,30,8},
                },
                {
                    {0,0,0,0},
                    {60,50,50,40},   //Arma Final
                    {65,50,50,45},
                    {70,45,50,50},
                    {70,45,50,55},
                    {70,40,50,60},
                },
            },
            {
                {
                    {10,80,80,0},    //Enemigo Base
                    {12,80,80,0},
                    {14,80,80,0},
                    {16,80,80,0},
                    {18,80,80,0},
                },
                {
                    {15,80,100,0},    //Enemigo Fuerte
                    {17,80,100,0},
                    {19,80,100,0},
                    {20,80,100,0},
                    {22,80,100,0},
                },
                {
                    {30,60,110,0},   //Nave Base
                    {32,60,110,0},
                    {34,60,110,0},
                    {36,60,110,0},
                    {38,60,110,0},
                },
                {
                    {40,70,150,0},   //Nave Fuerte
                    {42,70,150,0},
                    {44,70,150,0},
                    {46,70,150,0},
                    {48,70,150,0},
                },
                {
                    {50,80,100,0},   //Enemigo Final
                    {54,80,100,0},
                    {58,80,100,0},
                    {62,80,100,0},
                    {65,80,100,0},
                },
            }
        };
        damage = gun [enemy][id][level][0];
        delay = gun [enemy][id][level][1];
        speed = gun [enemy][id][level][2];
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
