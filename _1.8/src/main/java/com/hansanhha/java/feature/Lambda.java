package hsh.demo.jjjjava.feature;

/*
    참고하기 유용한 문서
    https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html
 */
class Lambda {

    /**
     * 람다식 문법
     * () -> {}
     * 메서드 파라미터 화살표 메서드 바디
     *
     * 파라미터가 하나라면 괄호 () 생략 가능
     * 바디가 하나의 식이라면 괄호 {} 생략 가능 (또는 void 메서드 호출)
     * return 문 또는 statement 사용 시 {} 필수
     *
     * 람다식을 전달할 메서드에서 기대하는 타입을 target type이라고 함
     * 람다식의 type은 target type에 의해 결정
     * -> 하나의 람다식이 여러 개의 type으로 결정될 수 있음
     */
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
