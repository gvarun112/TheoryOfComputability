package mod3_dfa;
public class Mod3_DFA {
    private static final int q0 = 0;
    private static final int q1 = 1;
    private static final int q2 = 2;
    private static final int q3 = 3;
    private int state;
    static private int delta(int s, char c) {
        switch(s) 
        {
                case q0 : switch(c) 
                {
                case '0' : System.out.println("q0 + 0"); return q0;
                case '1' : System.out.println("q0 + 1"); return q1;
                default : System.out.println("q0 + def"); return q3;
                }
                case q1 : switch(c) 
                {
                case '0' : System.out.println("q1 + 0"); return q2;
                case '1' : System.out.println("q1 + 1"); return q0;
                default : System.out.println("q1 + def"); return q3;
                }
                case q2 : switch(c) 
                {
                case '0' : System.out.println("q2 + 0"); return q1;
                case '1' : System.out.println("q2 + 1"); return q2;
                default : System.out.println("q2 + def"); return q3;
                }
                default : System.out.println("default"); return q3;
        }
    }
    boolean processString(String in) {
        for(int i = 0; i < in.length(); i++) {
            char c = in.charAt(i);
            state = delta(state, c);
        }
        return state == q0;
    }
    
    void runTests() {
        String s1 = "0";
        String s2 = "10";
        String s3 = "11";
        String s4 = "110";
        String s5 = "1001";
        System.out.println(s1 + " : " + processString(s1));
        state = q0;
        System.out.println(s2 + " : " + processString(s2));
        state = q0;
        System.out.println(s3 + " : " + processString(s3));
        state = q0;
        System.out.println(s4 + " : " + processString(s4));
        state = q0;
        System.out.println(s5 + " : " + processString(s5));
    }
}