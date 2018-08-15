package com.iglassusa.custom_gl_test;

public class DirectionThread extends Thread{

    private GamePanel mv;
    private int direction;
    public boolean canRun;

    public DirectionThread(GamePanel mv,int direction){
        this.mv=mv;
        this.direction=direction;
    }

    @Override
    public void run() {
        while (canRun) {
            mv.setDirection(direction);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
