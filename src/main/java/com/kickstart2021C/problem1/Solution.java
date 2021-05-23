package com.kickstart2021C.problem1;

import java.math.BigInteger;
import java.time.Duration;
import java.time.Instant;
import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

class Solution {

    public static void main(String[] args) throws Exception {

        // Smaller Strings

        // T number of test cases
        // N number of characters in S
        // K the first K characters of the english alphabet are usable
        // S String of length N consisting of lower case letters

        // file format
        // T
        // N K
        // S
        // 1
        // 2 3
        // bcs

        Boolean printTime = Boolean.TRUE;

        Instant start = Instant.now();

        Scanner input = new Scanner(new InputStreamReader(System.in));
        int t = input.nextInt();
        for (int i = 1; i <= t; ++i) {
            int n = input.nextInt();
            int k = input.nextInt();
            String s = input.next();
            BigInteger sum = new BigInteger("0");
            char[] sArray = s.toCharArray();
            char[] guess = new char[n];
            Arrays.fill(guess, 'a');
            if(isLexicographicallyLower(n, guess, sArray) && isPalindrome(guess)){
                sum = sum.add(BigInteger.ONE);
            }
            while (isLexicographicallyLower(n, guess, sArray)) {
                guess = findNextPalindrome(n, guess, k);
                if(isLexicographicallyLower(n, guess, sArray)) {
                    sum = sum.add(BigInteger.ONE);
                }
            }
            System.out.println("Case #" + i + ": " + (sum.mod(BigInteger.TEN.pow(9).add(BigInteger.valueOf(7)))));
        }

        if(printTime) {
            Instant end = Instant.now();
            System.out.println(Duration.between(start, end));
        }
    }

    private static char[] findNextPalindrome(int length, char[] guess, int chars) {
        char[] maxArray = new char[length];
        char max = (char)('a' + chars - 1);
        Arrays.fill(maxArray, max);
        while (isLexicographicallyLower(length, guess, maxArray)) {
            guess = addOne(guess, max, maxArray);
            if(isPalindrome(guess)){
                return guess;
            }
        }
        return maxArray;
    }

    private static boolean isPalindrome(char[] copy) {
        for (int i = 0; i < copy.length; i++) {
            if(copy[i] != copy[copy.length-i-1]){
                return false;
            }
        }
        return true;
    }

    private static char[] addOne(char[] copy, char max, char[] maxArray) {
        if(copy[copy.length-1] < max){
            copy[copy.length-1] = (char)(copy[copy.length-1] + 1);
            return copy;
        } else {
            for (int i = 0; i < copy.length; i++) {
                if((copy.length-1-i) >= 0){
                    if(copy[copy.length-1-i] < max){
                        copy[copy.length-1-i] = (char)(copy[copy.length-1-i] + 1);
                        return copy;
                    } else {
                        copy[copy.length-1-i] = 'a';
                    }
                } else {
                    return maxArray;
                }
            }
        }
        return maxArray;
    }

    private static boolean isLexicographicallyLower(int length, char[] guess, char[] sArray) {
        for (int i = 0; i < length; i++) {
            if(guess[i] < sArray[i]){
                return true;
            } else if(guess[i] > sArray[i]){
                return false;
            }
        }
        return false;
    }
}
