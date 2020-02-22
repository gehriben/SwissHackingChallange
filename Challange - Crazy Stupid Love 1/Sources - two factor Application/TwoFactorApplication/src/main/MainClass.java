// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MainClass.java

package main;

import java.io.PrintStream;

// Referenced classes of package com.Gugli.twofactor.MainPackage:
//            Cryptor

public class MainClass
{

    public MainClass()
    {
    }

    public static void main(String args[])
    {
        if(args.length < 2)
        {
            System.out.println("Required arguments: secret seed");
            System.out.println("\tsecret - the secret password distributed by your admin (is different from your Gugli webapp password)");
            System.out.println("\tseed - the seed obtained from our webserver (https://gugli/two_factor_seed). The seed changes with time.");
            return;
        }
        String secret = args[0];
        String seed = args[1];
        if(!checkSecret(secret).booleanValue())
        {
            System.out.println("Go away, h4cker! You shall not pass");
            return;
        } else
        {
            System.out.println("Secret is correct! Welcome to");
            System.out.println("");
            System.out.println("                                                                          ");
            System.out.println("                                                                          ");
            System.out.println("        GGGGGGGGGGGGG                                     lllllll   iiii  ");
            System.out.println("     GGG::::::::::::G                                     l:::::l  i::::i ");
            System.out.println("   GG:::::::::::::::G                                     l:::::l   iiii  ");
            System.out.println("  G:::::GGGGGGGG::::G                                     l:::::l         ");
            System.out.println(" G:::::G       GGGGGGuuuuuu    uuuuuu     ggggggggg   gggggl::::l iiiiiii ");
            System.out.println("G:::::G              u::::u    u::::u    g:::::::::ggg::::gl::::l i:::::i ");
            System.out.println("G:::::G              u::::u    u::::u   g:::::::::::::::::gl::::l  i::::i ");
            System.out.println("G:::::G    GGGGGGGGGGu::::u    u::::u  g::::::ggggg::::::ggl::::l  i::::i ");
            System.out.println("G:::::G    G::::::::Gu::::u    u::::u  g:::::g     g:::::g l::::l  i::::i ");
            System.out.println("G:::::G    GGGGG::::Gu::::u    u::::u  g:::::g     g:::::g l::::l  i::::i ");
            System.out.println("G:::::G        G::::Gu::::u    u::::u  g:::::g     g:::::g l::::l  i::::i ");
            System.out.println(" G:::::G       G::::Gu:::::uuuu:::::u  g::::::g    g:::::g l::::l  i::::i ");
            System.out.println("  G:::::GGGGGGGG::::Gu:::::::::::::::uug:::::::ggggg:::::gl::::::li::::::i");
            System.out.println("   GG:::::::::::::::G u:::::::::::::::u g::::::::::::::::gl::::::li::::::i");
            System.out.println("     GGG::::::GGG:::G  uu::::::::uu:::u  gg::::::::::::::gl::::::li::::::i");
            System.out.println("        GGGGGG   GGGG    uuuuuuuu  uuuu    gggggggg::::::glllllllliiiiiiii");
            System.out.println("                                                   g:::::g                ");
            System.out.println("                                       gggggg      g:::::g                ");
            System.out.println("                                       g:::::gg   gg:::::g                ");
            System.out.println("                                        g::::::ggg:::::::g                ");
            System.out.println("                                         gg:::::::::::::g                 ");
            System.out.println("                                           ggg::::::ggg                   ");
            System.out.println("                                              gggggg                      ");
            String pin = Cryptor.generatePin(secret, seed);
            System.out.printf("Your pin is: %s\n", new Object[] {
                pin
            });
            System.out.println("Append the pin to your first token to obtain the SSH password.");
            System.out.println("Use your Gugli username and the obtained password to login.");
            return;
        }
    }

    public static Boolean checkSecret(String secret)
    {
    	return true;
        //return Boolean.valueOf(Cryptor.hashSecret(secret).equals("C3A17115CA9B500459C7402EC54B44EE92885F91CA5451B0F62786F033F99809"));
    }
}
