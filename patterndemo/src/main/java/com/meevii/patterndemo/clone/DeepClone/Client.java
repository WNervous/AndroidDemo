package com.meevii.patterndemo.clone.DeepClone;

import android.util.Log;

import java.io.IOException;

public class Client {

    public static void Test() {
        WeekLog weekLog = new WeekLog();
        weekLog.setName("Zhang san");
        weekLog.setContent("wo hen mang");
        weekLog.setDate("12");

        WeekLog clone = weekLog.clone();
        clone.setDate("13");

        Log.d("Client", weekLog.toString());
        Log.d("Client", clone.toString());
    }

    public static void TestWithAttachment() {
        WeekLog weekLog = new WeekLog();
        Attachment attachment = new Attachment();
        attachment.setName("attachment");
        weekLog.setAttachment(attachment);
        weekLog.setName("Zhang san");
        weekLog.setContent("wo hen mang");
        weekLog.setDate("12");

        WeekLog clone = weekLog.clone();

        System.out.println("周报是否相同？ " + (weekLog == clone));
        //比较附件
        System.out.println("附件是否相同？ " + (weekLog.getAttachment() == clone.getAttachment()));
    }

    public static void DeepCloneTest() {
        WeekLog weekLog = new WeekLog();
        Attachment attachment = new Attachment();
        attachment.setName("attachment");
        weekLog.setAttachment(attachment);
        weekLog.setName("Zhang san");
        weekLog.setContent("wo hen mang");
        weekLog.setDate("12");

        WeekLog clone;
        try {
            clone = weekLog.deepClone();
            System.out.println("周报是否相同？ " + (weekLog == clone));
            //比较附件
            System.out.println("附件是否相同？ " + (weekLog.getAttachment() == clone.getAttachment()));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
