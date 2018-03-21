/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package printFinal;



/**
 *
 * @author Jacob Mahto
 */
public class MultPHandlerE {
    static int lifetime;
    
    public static void handler(int begin,int times,int i){
        System.out.println("HANDLER EXECUTED!!!");
        System.out.println("---"+begin+"---"+times+"----"+i);
    if(begin==0){
        System.out.println("BEGIN BLOCK!!!");
        lifetime=times;
    printSimpRepE.jac();
    i=begin;
    reportCardE.studentShuffler(0);
    printSimpRepE.printPages=times;
    printSimpRepE.main(null);
    }
   if(i<lifetime && i!=0){
       System.out.println("AFTER BENGIN BLOCK!!!");
   reportCardE.studentShuffler(i);
   }     
     System.out.println("HANDLER FINISHED!!!"); 
        
    }
     
    
    
}
