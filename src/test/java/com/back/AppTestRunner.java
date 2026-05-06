package com.back;

import com.back.standard.util.TestUtil;

import java.io.ByteArrayOutputStream;

public class AppTestRunner {
    public static String run(String input) {
        AppContext.init(TestUtil.genScanner(input + "\n종료"));
        ByteArrayOutputStream byteArrayOutputStream = TestUtil.setOutToByteArray();

        new App().run();

        String out = byteArrayOutputStream.toString().trim();
        TestUtil.clearSetOutToByteArray();

        return out;
    }
}
