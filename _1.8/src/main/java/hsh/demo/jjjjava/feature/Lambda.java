package hsh.demo.jjjjava.feature;

class Lambda {

    static Runnable lambda = () -> System.out.println("hsh.demo.jjjjava.feature.Lambda expression");

    static class NormalImpl implements Runnable {

        @Override
        public void run() {
            System.out.println("Normal implementation");
        }
    }

    public static void run() {
        System.out.println("1.8 - hsh.demo.jjjjava.feature.Lambda, using Runnable");

        Thread thread = new Thread(new NormalImpl());
        thread.start();

        lambda.run();
    }

}
