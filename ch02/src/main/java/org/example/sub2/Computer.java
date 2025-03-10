package org.example.sub2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("com")
public class Computer {

    /* JAVA 에서는 이런식으로 생성자에서 초기화!
    public Computer(Cpu cpu, Ram ram, Hdd hdd) {
        this.cpu = cpu;
        this.ram = ram;
        this.hdd = hdd;
        }
    */


    //필드 주입
    @Autowired
    private Cpu cpu;

    //생성자 주입
    private Ram ram;

    @Autowired
    public Computer(Ram ram) {
        this.ram = ram;
    }

    //세터(Setter) 주입
    @Autowired
    private Hdd hdd;

    public void setHdd(Hdd hdd) {
        this.hdd = hdd;
    }



    public void show(){
        cpu.show();
        ram.show();
        hdd.show();
    }



}
