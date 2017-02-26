package test;

import java.lang.reflect.Constructor;

public class SingletonReflection {

    /**
     * たった1つのインスタンス？ 〜Singletonを破ってみよう〜。
     * http://tercel-tech.hatenablog.com/entry/2013/05/19/003944
     *
     * @param args 引数
     */
    public static void main(String[] args) {
        // 通常は、クラスメソッド経由でインスタンスを取得する
        Singleton instance = Singleton.getInstance();
        instance.setValue(100);

        // しかし、悪い人はこうやってprivateコンストラクタを破る
        Singleton anotherInstance = null;
        try {
            final String className = "test.Singleton";
            Constructor<?> constructor = Class.forName(className).getDeclaredConstructor();
            constructor.setAccessible(true);
            anotherInstance = (Singleton) constructor.newInstance();
            anotherInstance.setValue(50);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // 中身の出力
        if (instance != null) {
            System.out.println("instance: " + instance);
        }

        if (anotherInstance != null) {
            System.out.println("anotherInstance: " + anotherInstance);
        }

        // 同一性比較
        if (!instance.equals(anotherInstance)) {
            System.out.println("両インスタンスは異なります");
        }
        if (instance != anotherInstance) {
            System.out.println("両インスタンスは異なります");
        }
    }
}
