package me.day22.functional.pure;

public class NoState {
    public static void main(String[] args) {
        // int o = 10; //---(1)
        Func func = new Func() {
            // int o = 10; //---(2)
            @Override
            public int method(int i) {
                int o = 10; //---(3)
                return i + o;
            }
        };
    }
}