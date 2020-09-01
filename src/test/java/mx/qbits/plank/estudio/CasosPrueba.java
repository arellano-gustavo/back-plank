package mx.qbits.plank.estudio;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class CasosPrueba {
    
    @Test
    public void calc2() {
        int k6=9;
        int[] a6 = {25, 53, 96, 30, -57, 32, 3, 33, 83, 81, -35, -92, 34, 79, 75, 71, -14, 58, -43, 91};
        int r6 = Solution2.findPairs(a6, k6);
        assertTrue(r6==1);

        int k7=52;
        int[] a7 = {88, 6, -24, 35, -29, 25, 13, 15, 29, 70, 45, 40, 32, 40, -27, -48, 46, 62};
        int r7 = Solution2.findPairs(a7, k7);
        assertTrue(r7==1);

        int k9=39;
        int[] a9 = {18, -82, -99, 14, 17, 53, 63, 80, 41, 0, 22, -14, 61, 76};
        int r9 = Solution2.findPairs(a9, k9);
        assertTrue(r9==3);

        int k10=23;
        int[] a10 = {-60, 6, -58, 96, 56, 22, 9, 26, -81, 11, -76, 91, 69, 10, 1, 72, 61, 51, -87};
        int r10 = Solution2.findPairs(a10, k10);
        assertTrue(r10==1);

        int k11=31;
        int[] a11 = {55, -14, 40, 61, 91, 51, -70, 52, -69, 70, -48, 12, 91, -77, 20, -30, 67, 35, -45, -81, 46, 84, 11};
        int r11 = Solution2.findPairs(a11, k11);
        assertTrue(r11==2);

        int k14=17;
        int[] a14 = {25, -85, 18, 96, 19, 83, 73, 90, 80, 2, 54, 5, 68, 19, 64, 26, 88, 71, 89, 24, 39};
        int r14 = Solution2.findPairs(a14, k14);
        assertTrue(r14==4);

        int k15=28;
        int[] a15 = {-24, 25, -32, 89, 25, 83, 30, 71, 41, 61, 6, 39, 33, 91, -3, 33, 94, 53, 45, 12, 17, -28, 55, 66};
        int r15 = Solution2.findPairs(a15, k15);
        assertTrue(r15==7);

        int k16=42;
        int[] a16 = {80, 48, 88, 7, -97, 20, 22, -48, -86, 62, 30, 9, 0, 43, 37, 15, 44, 68, -37, 74};
        int r16 = Solution2.findPairs(a16, k16);
        assertTrue(r16==1);

        int k20=52;
        int[] a20 = {19, -45, 17, 41, -46, -97, 45, 23, 79, -24, 73, 76, 91, 87, 6, 71, 13, 62, 70, 14};
        int r20 = Solution2.findPairs(a20, k20);
        assertTrue(r20==3);

        int k27=35;
        int[] a27 = {17, 76, 93, 75, -6, 26, 30, 51, 18, -98, 25, 24, 50, 44, 51, 60, -26, 1, 52, 50, 39};
        int r27 = Solution2.findPairs(a27, k27);
        assertTrue(r27==2);

        int k28=47;
        int[] a28 = {49, 2, 91, 24};
        int r28 = Solution2.findPairs(a28, k28);
        assertTrue(r28==1);

        int k30=42;
        int[] a30 = {25, 21, 30, 97, 31, -27, 74, 69, 56, 50, 30, 85, 39, 71, 81, 45};
        int r30 = Solution2.findPairs(a30, k30);
        assertTrue(r30==1);

        int k32=12;
        int[] a32 = {63, 80, 40, 3, 31, 68, -61, -38};
        int r32 = Solution2.findPairs(a32, k32);
        assertTrue(r32==1);

        int k34=12;
        int[] a34 = {9, 25, 62, 43, 68, 91, 80, -1, 9, 38, 24, 40, -65};
        int r34 = Solution2.findPairs(a34, k34);
        assertTrue(r34==1);

        int k36=5;
        int[] a36 = {58, -83, 90, 24, 13, 54, -48, 45, 40, -75, 85, 74, 64, 18};
        int r36 = Solution2.findPairs(a36, k36);
        assertTrue(r36==3);

        int k37=53;
        int[] a37 = {48, 98, 51, 15, 88, -90, -3, -15, 2, 15, 67, 8, -92, 81, -47, -36, -85, 55, 40, 57};
        int r37 = Solution2.findPairs(a37, k37);
        assertTrue(r37==1);

        int k38=42;
        int[] a38 = {24, -81, 78, -44, 56, 34, 81, -25, 25, 67, 44, 91, 2, 69, 65, -8, -40, -93, 22};
        int r38 = Solution2.findPairs(a38, k38);
        assertTrue(r38==4);

        int k39=41;
        int[] a39 = {8, 14, -41, 32, 36, 71, -52, 50, 86, 0, -51, 60, -62, 90, 1, 44};
        int r39 = Solution2.findPairs(a39, k39);
        assertTrue(r39==1);

        int k41=14;
        int[] a41 = {95, 39, 73, 80, -90, 45, -47, 24, 19, 42, 53, 54, 70, 92, 52, 23, 68, 67, 86, 89, 16, 56, 13};
        int r41 = Solution2.findPairs(a41, k41);
        assertTrue(r41==5);

        int k42=30;
        int[] a42 = {50, -82, -19, 78, 51, -63, 22, 96, -67, -81, -57, 92, 28, 56, 20, 45, 59, 21, 4, 19, 18, 50, -64, 38};
        int r42 = Solution2.findPairs(a42, k42);
        assertTrue(r42==2);

        int k50=13;
        int[] a50 = {70, 74, -46, 36, -73, 65, 50, 48, 38, -19, 80, 12, 87, 41, 6, -69, 20, 86, 85, 15};
        int r50 = Solution2.findPairs(a50, k50);
        assertTrue(r50==1);

        int k51=10;
        int[] a51 = {1, 83, 55, 62, 65, 19, -12, 67, -78, 90, 52};
        int r51 = Solution2.findPairs(a51, k51);
        assertTrue(r51==2);

        int k54=6;
        int[] a54 = {94, 22, 97, -22, 49, 20, -80, 29, 20, 71, -48, 52, 4, 64, 34, 98, 43, -20, 26, 14, 25, 62, 80, -72};
        int r54 = Solution2.findPairs(a54, k54);
        assertTrue(r54==3);

        int k60=8;
        int[] a60 = {24, 76, 56, 74, 1, 70, 33, 35, 16, 59, 83, -30, -9, 7, 93, 46, 2};
        int r60 = Solution2.findPairs(a60, k60);
        assertTrue(r60==1);

        int k62=20;
        int[] a62 = {87, 75, 66, 14, 95, 15, -13, 81, 50, 14, 72, 88, 70, 74, 1, 92, 84, -88, 27, -18, -22, -24, 71, -56};
        int r62 = Solution2.findPairs(a62, k62);
        assertTrue(r62==3);

        int k63=29;
        int[] a63 = {-54, 43, 40, 68, -53, 77, -83, 80, 85, 88, 51, -99, 55, 73, -8, -39, 75, 85, 63, 43, 87, 44};
        int r63 = Solution2.findPairs(a63, k63);
        assertTrue(r63==3);

        int k65=28;
        int[] a65 = {81, 70, 4, 45, 14, -97, 67, -20, 13, 85, -16, 20, 95, 60, 64, 72};
        int r65 = Solution2.findPairs(a65, k65);
        assertTrue(r65==1);

        int k66=30;
        int[] a66 = {89, 78, 94, 18, 33, 17, 62, 30, 47, -5, 97, 2, 20, -16, 52, 61, 38, 56, 5, 3, 91};
        int r66 = Solution2.findPairs(a66, k66);
        assertTrue(r66==3);

        int k70=49;
        int[] a70 = {73, 60, -30, 60, 58, 23, 9, 92, 89};
        int r70 = Solution2.findPairs(a70, k70);
        assertTrue(r70==1);

        int k71=20;
        int[] a71 = {-20, 58, 91, 80, 0, 63, 92, 37, 12, -99, -58, 44, 73, -28, 87, 41, 61, 37, 77, 66, 65};
        int r71 = Solution2.findPairs(a71, k71);
        assertTrue(r71==2);

        int k72=13;
        int[] a72 = {-50, 84, 56, 46, 12, 51, 59, -86, 57, 26, 79, -26, 65, 81, 9, 20, 46, 71, 8, 13, 24, -63};
        int r72 = Solution2.findPairs(a72, k72);
        assertTrue(r72==4);

        int k75=18;
        int[] a75 = {53, 5, 71, 76, 18, 62, 81, 0, 27, 84, -61, -35, 64, 55, 63, 3, 64};
        int r75 = Solution2.findPairs(a75, k75);
        assertTrue(r75==3);

        int k77=41;
        int[] a77 = {11, 3, 71, 18, 44, 98, 89, 82, 55, 41, 89, 72};
        int r77 = Solution2.findPairs(a77, k77);
        assertTrue(r77==2);

        int k78=52;
        int[] a78 = {73, 1, 83, 22, 24, 45, 5, 66, 20, -22, 12, 68, 23, 73, -17, 30, 31, -9, -31};
        int r78 = Solution2.findPairs(a78, k78);
        assertTrue(r78==2);

        int k79=43;
        int[] a79 = {85, 83, 65, -13, 14, 49, 57, 43, -40, -14};
        int r79 = Solution2.findPairs(a79, k79);
        assertTrue(r79==1);

        int k80=19;
        int[] a80 = {59, 40, 98, -85, -33, 99, 52, 13, 75, 28, 94, 64, 25, -28, 64, 4, 0, 11, 20, 66, 18};
        int r80 = Solution2.findPairs(a80, k80);
        assertTrue(r80==2);

        int k82=21;
        int[] a82 = {60, 4, 42, 50, 15, 77, 36};
        int r82 = Solution2.findPairs(a82, k82);
        assertTrue(r82==1);

        int k83=22;
        int[] a83 = {-89, -43, 15, 82, -37, -59, 37, -82, 59, 70, 11, 21, 80, 82, 71, 60, 23, 43, 78, 1};
        int r83 = Solution2.findPairs(a83, k83);
        assertTrue(r83==6);

        int k85=35;
        int[] a85 = {-71, 0, 33, 16, 10, 2, 92, -76, 79, 24, 57, -41, 13, 41, 65, 90, 18, 75};
        int r85 = Solution2.findPairs(a85, k85);
        assertTrue(r85==2);

        int k86=20;
        int[] a86 = {99, 15, 61, 79, 55, 81, -15};
        int r86 = Solution2.findPairs(a86, k86);
        assertTrue(r86==2);

        int k87=14;
        int[] a87 = {6, 70, 46, 69, 42, -76, -20, -99, -98, -72, 98, 44, 33, 53, 83, 20, 13, -47, 49, -27, 11, 84, 48, 38};
        int r87 = Solution2.findPairs(a87, k87);
        assertTrue(r87==4);

        int k88=7;
        int[] a88 = {38, 13, 94, 11, 65, 0, 68, 35, 26, 36, -56, 68, 61, 75, 12, 99, 32, 74, -42, 20};
        int r88 = Solution2.findPairs(a88, k88);
        assertTrue(r88==3);

        int k90=30;
        int[] a90 = {75, 30, 15, 85, 51, -55, -71, 77, -97, -69, 78, 42, 81, -48, 2, -63, -14, 59, 64, 99, -18};
        int r90 = Solution2.findPairs(a90, k90);
        assertTrue(r90==2);

        int k95=48;
        int[] a95 = {12, -80, 20, 33, 8, 76, 23, 63, 87, 21, 35, 20, 6, 99, 84, 30, 28, 94, 54, 91, 93, 34, 56, 95};
        int r95 = Solution2.findPairs(a95, k95);
        assertTrue(r95==3);

        int k96=23;
        int[] a96 = {14, 72, 36, 81, 39, -34, 28, 61, 41, 92, 29, 59, 79, 72, 54, 64, 10};
        int r96 = Solution2.findPairs(a96, k96);
        assertTrue(r96==2);

        int k97=25;
        int[] a97 = {21, 87, 82, 30, 60, 73, -14, 68, 82, 14, 43, 41, -83, -54, -56, 83};
        int r97 = Solution2.findPairs(a97, k97);
        assertTrue(r97==1);
    }

}
