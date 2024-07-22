package org.example;

import com.google.cloud.functions.invoker.runner.Invoker;

public class Main {
    public static void main(String[] args) throws Exception {
        Invoker.main(new String[] {
                "--target", "org.example.HelloHttpFunction",
                "--port", "8081"
        });
    }
}
