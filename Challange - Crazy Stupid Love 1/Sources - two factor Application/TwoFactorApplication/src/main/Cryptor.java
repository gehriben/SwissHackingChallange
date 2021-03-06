package main;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Cryptor.java

import java.math.BigInteger;

// Referenced classes of package com.Gugli.twofactor.MainPackage:
//            CryptoUtils

public class Cryptor
{

    public Cryptor()
    {
    }

    public static String hashSecret(String input)
    {
        return CryptoUtils.sha256(input);
    }

    public static String f0(String b, String k)
    {
        CryptoUtils utils = new CryptoUtils();
        return utils.xor(b, utils.or(b, utils.not(k)));
    }

    public static String f1(String b, String k)
    {
        CryptoUtils utils = new CryptoUtils();
        return utils.xor(b, utils.or(k, utils.not(b)));
    }

    public static String f2(String b, String k)
    {
        CryptoUtils utils = new CryptoUtils();
        return utils.xor(b, utils.and(k, utils.not(b)));
    }

    public static String f3(String b, String k)
    {
        CryptoUtils utils = new CryptoUtils();
        return utils.xor(b, utils.and(b, utils.not(k)));
    }

    public static String generatePin(String secret, String seed)
    {
        int ROUNDS_COUNT = 256;
        String key = secret;
        String seed_hash = CryptoUtils.sha1(seed).substring(0, 16).toLowerCase();
        String bins[] = new String[4];
        bins[0] = seed_hash.substring(0, 4);
        bins[1] = seed_hash.substring(4, 8);
        bins[2] = seed_hash.substring(8, 12);
        bins[3] = seed_hash.substring(12, 16);
        for(int i = 0; i < ROUNDS_COUNT; i++)
        {
            key = CryptoUtils.sha1(key).substring(0, 16).toLowerCase();
            bins[0] = f3(bins[3], key);
            bins[1] = f0(bins[0], key);
            bins[2] = f1(bins[1], key);
            bins[3] = f2(bins[2], key);
        }

        int result = 0;
        for(int i = 0; i < 4; i++)
        {
            BigInteger tmp = new BigInteger(bins[i].getBytes());
            result += tmp.intValue();
        }

        if(result < 0)
            result = -result;
        result %= 1000;
        return String.valueOf(result);
    }
}
