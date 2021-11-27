package com.hgz.algorithm;

/**
 * @author hgz
 * @version 1.0
 * @date 2021/11/1 21:58
 */
public class MaoPao {



    public static void sort(int[] array){
        boolean flag=true;
        for (int i = 0; i < array.length-1; i++) {
            for (int j = 0; j < array.length-1-i; j++) {
                    if(array[j]>array[j+1]){
                        array[j+1]=array[j]^array[j+1];
                        array[j]=array[j+1]^array[j];
                        array[j+1]=array[j]^array[j+1];
                    }
            }
        }

        for (int i : array) {
            System.out.println(i);
        }
    }


    public static void main(String[] args) {
        int[] array={1,9,6,8,7,3,7,1,89,96,2,4,78};
        sort(array);
    }

}
