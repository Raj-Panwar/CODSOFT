

import java.util.Scanner;
import java.util.Stack;

public class GRADECALCULATOR {
    private static int g = 0;
    public static void calc(Stack<Integer> s){
        int total=0;
        float percentage ;
        int x;
        int n = s.size();
        g=g+1;
        while(s.size()>0){
            x=s.pop();
            total+=x;
        }
        System.out.println("TOTAL MARKS----> " + total);
        percentage =(float) total/n;
        System.out.println("PERCENTAGE-----> "+ percentage);
        if(percentage>=95.0){
            System.out.println("GRADE---> A+");
        }
        else if(percentage>=90.0 && percentage<95.0){
            System.out.println("GRADE----> A");
        }
        else if(percentage>=85.0 && percentage<90.0){
            System.out.println("GRADE----> B+");
        }
        else if(percentage>=80.0 && percentage<85.0){
            System.out.println("GRADE----> B");
        }
        else if(percentage>=75.0 && percentage<80.0){
            System.out.println("GRADE----> C+");
        }
        else if(percentage>=65.0 && percentage<75.0){
            System.out.println("GRADE----> C");
        }
        else if(percentage>=60.0 && percentage<65.0){
            System.out.println("GRADE----> D");
        }else if(percentage>=50.0 && percentage<60.0){
            System.out.println("GRADE----> E");
        }else{
            System.out.println("GRADE----> F");     
        }

    }
    
    public static void main(String[] args) {
        Stack<Integer> subjects = new Stack<>();int n;
        int mark;
        String ans;
        System.out.println("_________________________________________\n");
        System.out.println("\t GRADE CALCULATOR \t  \n");
        System.out.println("_________________________________________\n");
        while(true){
            Scanner sc = new Scanner(System.in);
            System.out.print("ENTER NUMBER OF SUBJECTS : ");
            n=sc.nextInt();
            while (n <= 0) {
                System.out.println("Number of subjects must be greater than 0. Please re-enter.");
                System.out.print("ENTER NUMBER OF SUBJECTS : ");
                n = sc.nextInt();
            }

            System.out.println("ENTER MARKS (out of 100): ");
            for (int i = 1; i <= n; i++) {
                while (true) { 
                    System.out.print("MARKS OF SUBJECT " + i + " : ");
                    mark = sc.nextInt();

                    if (mark >= 0 && mark <= 100) { 
                        subjects.push(mark);
                        break;
                    } else {
                        System.out.println("Invalid mark! Please enter marks between 0 and 100.");
                    }
                }
            }
            
        System.out.println("MARKS------> "+ subjects);
        calc(subjects);
        System.out.println("WANT TO CALCULATE AGAIN (yes/no)? :");
        ans=sc.next();
        if(ans.toLowerCase().equals("yes")){
            sc.close();
            continue;
        }
        else{
            sc.close();
            break;

        }
        
        
    }
    System.out.println("GRADE CALCULATION COMPLETED "+g+ " times");
    

        
    }
}
