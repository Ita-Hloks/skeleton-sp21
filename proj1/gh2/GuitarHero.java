package gh2;

import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

public class GuitarHero {
    public static final double CONCERT = 440.0;
    private static final String TEXT = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

    public static void creatKeyboard(GuitarString[] kb) {
        int len = TEXT.length();

        for (int i = 0; i < len; i++) {
            GuitarString stringX = new GuitarString(CONCERT * Math.pow(2, i / 12.0));
            kb[i] = stringX;
        }
        /* If you put the key we haven`t define, you will learn noting*/
    }

    public static void main(String[] args) {
        /* create two guitar strings, for concert A and C */
        GuitarString[] kb = new GuitarString[TEXT.length()];
        creatKeyboard(kb);
        GuitarString stringA = new GuitarString(CONCERT);
        int i = 0;
        while (true) {
            boolean hadPluck = false;
            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                for (; i < TEXT.length(); i++) {
                    if (TEXT.charAt(i) == key) {
                        kb[i].pluck();
                        hadPluck = true;
                        break;
                    }
                }
                if (!hadPluck) {
                    i--;
                    kb[i] = stringA;
                    kb[i].pluck();
                }
            }
            /* compute the superposition of samples */
            double sample = stringA.sample() + kb[i].sample();

            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */
            stringA.tic();
            kb[i].tic();
        }
    }
}
