package cyk;
import java.util.ArrayList;

public class CYK {
    
    static CNFG GRAMMAR1 = null;
    static CNFG GRAMMAR2 = null;
    
    // sample CNF grammar
    static {
        GRAMMAR1 = new CNFG();
        // 1. S -> AB
        GRAMMAR1.addProduction("S", "A", "B");
        // 2. S --> BC
        GRAMMAR1.addProduction("S", "B", "C");
        // 3. A --> BA
        GRAMMAR1.addProduction("A", "B", "A");
        // 4. A --> a
        GRAMMAR1.addProduction("A", "a");
        // 5. B --> CC
        GRAMMAR1.addProduction("B", "C", "C");
        // 6. B --> b
        GRAMMAR1.addProduction("B", "b");
        // 7. C --> AB
        GRAMMAR1.addProduction("C", "A", "B");
        // 8. C --> a
        GRAMMAR1.addProduction("C", "a");
    }
    
    // Another sample CNF grammar
    static {
        GRAMMAR2 = new CNFG();
        // 1. S -> AB
        GRAMMAR2.addProduction("S", "A", "B");
        // 2. S --> BB
        GRAMMAR2.addProduction("S", "B", "B");
        // 3. A --> CC
        GRAMMAR2.addProduction("A", "C", "C");
        // 4. A --> AB
        GRAMMAR2.addProduction("A", "A", "B");
        // 5. A --> a
        GRAMMAR2.addProduction("A", "a");
        // 6. B --> BB
        GRAMMAR2.addProduction("B", "B", "B");
        // 7. B --> CA
        GRAMMAR2.addProduction("B", "C", "A");
        // 8. B --> b
        GRAMMAR2.addProduction("B", "b");
        // 9. C --> BA
        GRAMMAR2.addProduction("C", "B", "A");
        // 10. C --> AA
        GRAMMAR2.addProduction("C", "A", "A");
        // 11. C --> b
        GRAMMAR2.addProduction("C", "b");
    }
    
    static boolean isInCFL(String x, CNFG cnfg) {
        if (x.length() == 0) {
            return false;
        }
        DTable DT = new DTable(x);
        // add your code here
        for(int s = 1; s<x.length(); s++) {
            DT.addToCell(s, 1, GRAMMAR1.fetchLHS(new StringBuilder().append(x.charAt(s)).toString()));
        }
        for ( int j = 2; j < x.length(); j++) {
            for ( int i = 1; i < x.length() - j + 1; i++) {
                DT.addToCell(i, j, new ArrayList<String>());
                for ( int k = 1; k < j-1; k++) {
                    ArrayList<String> B = DT.getCell(i, k);
                    ArrayList<String> C = DT.getCell(i+k,j-k);
                    DT.addToCell(i, j, GRAMMAR1.fetchLHS(new StringBuilder().append(B).append(C).toString()));
                }
            }
        }
        // Get the cell(1, n) and check if the start symbol of the
        // grammar is in it
        
        ArrayList<String> cell = DT.getCell(new Integer(1), new Integer(x.length()));
        return cell.indexOf(cnfg.getStartSymbol()) != -1;
    }
    
    static void test1() { 
        System.out.println("===== Test 1 ====== ");
        GRAMMAR1.display();
        System.out.println("Input string: " + "baaba");
        boolean rslt = CYK.isInCFL("baaba", GRAMMAR1);
        System.out.println("Result = " + rslt + "\n");
        //System.out.println(GRAMMAR1.fetchLHS(new StringBuilder().append('a').toString()));
    }
    
    static void test2() { 
        System.out.println("===== Test 2 ====== ");
        GRAMMAR1.display();
        System.out.println("Input string: " + "baaa");
        boolean rslt = CYK.isInCFL("baaba", GRAMMAR1);
        System.out.println("Result = " + rslt + "\n");
    }

   
    static void test3() {
        System.out.println("===== Test 3 ====== ");
        GRAMMAR1.display();
        System.out.println("Input string: " + "baba");
        boolean rslt = CYK.isInCFL("baba", GRAMMAR1);
        System.out.println("Result = " + rslt + "\n");
    }

    static void test4() {
        System.out.println("===== Test 4 ====== ");
        GRAMMAR1.display();
        System.out.println("Input string: " + "baaabab");
        boolean rslt = CYK.isInCFL("baaabab", GRAMMAR1);
        System.out.println("Result = " + rslt + "\n");
    }
    
    static void test5() {
        System.out.println("===== Test 5 ====== ");
        GRAMMAR1.display();
        System.out.println("Input string: " + "aabb");
        boolean rslt = CYK.isInCFL("aabb", GRAMMAR2);
        System.out.println("Result = " + rslt + "\n");
    }

    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();
        test5();
    }
}
