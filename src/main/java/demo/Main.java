package demo;

import com.google.cloud.functions.invoker.runner.Invoker;

public class Main {
    public static void main(String[] args) throws Exception {
        Invoker.main(new String[] {
                "--target", "demo.GetResumeData",
                "--port", "8081"
        });
    }
}
