package main;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CryptoUtils.java

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

// Referenced classes of package com.Gugli.twofactor.MainPackage:
//            BinaryOpLambda

public class CryptoUtils
{
    public class andLambda
        implements BinaryOpLambda
    {

        public byte operation(byte x, byte y)
        {
            return (byte)(x & y);
        }

        final CryptoUtils this$0;

        public andLambda()
        {
            super();
            this$0 = CryptoUtils.this;

        }
    }

    public class orLambda
        implements BinaryOpLambda
    {

        public byte operation(byte x, byte y)
        {
            return (byte)(x | y);
        }

        final CryptoUtils this$0;

        public orLambda()
        {
            super();
            this$0 = CryptoUtils.this;

        }
    }

    public class xorLambda
        implements BinaryOpLambda
    {

        public byte operation(byte x, byte y)
        {
            return (byte)(x ^ y);
        }

        final CryptoUtils this$0;

        public xorLambda()
        {
            super();
            this$0 = CryptoUtils.this;

        }
    }


    public CryptoUtils()
    {
    }

    public static String sha256(String input)
    {
        String sha1 = null;
        try
        {
            MessageDigest msdDigest = MessageDigest.getInstance("SHA-256");
            msdDigest.update(input.getBytes("US-ASCII"), 0, input.length());
            sha1 = ByteToHex.bytesToHex(msdDigest.digest());
        }
        catch(Exception e)
        {
            return null;
        }
        return sha1;
    }

    public static String sha1(String input)
    {
        String sha1 = null;
        try
        {
            MessageDigest msdDigest = MessageDigest.getInstance("SHA-1");
            msdDigest.update(input.getBytes("US-ASCII"), 0, input.length());
            sha1 = ByteToHex.bytesToHex(msdDigest.digest());
        }
        catch(Exception e)
        {
            return null;
        }
        return sha1;
    }

    public static String doBinOp(String a, String b, BinaryOpLambda f)
    {
        byte x[];
        try
        {
            x = a.getBytes("US-ASCII");
        }
        catch(UnsupportedEncodingException e1)
        {
            System.out.println("could not decode a");
            return null;
        }
        byte y[];
        try
        {
            y = b.getBytes("US-ASCII");
        }
        catch(UnsupportedEncodingException e1)
        {
            System.out.println("could not decode b");
            return null;
        }
        int l = x.length >= y.length ? y.length : x.length;
        byte z[] = new byte[l];
        for(int i = 0; i < l; i++)
            z[i] = f.operation(x[i], y[i]);

        String res;
        try
        {
            res = new String(z, "US-ASCII");
        }
        catch(UnsupportedEncodingException e)
        {
            System.out.println("could not encode res");
            return null;
        }
        return res;
    }

    public String xor(String a, String b)
    {
        return doBinOp(a, b, new xorLambda());
    }

    public String and(String a, String b)
    {
        return doBinOp(a, b, new orLambda());
    }

    public String or(String a, String b)
    {
        return doBinOp(a, b, new andLambda());
    }

    public String not(String a)
    {
        byte x[];
        try
        {
            x = a.getBytes("US-ASCII");
        }
        catch(UnsupportedEncodingException e1)
        {
            System.out.println("could not decode a");
            return null;
        }
        byte z[] = new byte[x.length];
        for(int i = 0; i < x.length; i++)
            z[i] = (byte)(~x[i] & 0xff);

        String res;
        try
        {
            res = new String(z, "US-ASCII");
        }
        catch(UnsupportedEncodingException e)
        {
            System.out.println("could not encode res");
            return null;
        }
        return res;
    }
}
