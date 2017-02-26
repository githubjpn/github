package test;

/**
 * たった1つのインスタンス？ 〜Singletonを破ってみよう〜。
 * http://tercel-tech.hatenablog.com/entry/2013/05/19/003944
 *
 * @param args 引数
 */
public class Singleton {
    private static final Singleton instance = new Singleton();
    private int value;

    // コンストラクタ
    private Singleton() {
        value = 0;
    }

    // アクセサ
    public static Singleton getInstance() {
        return instance;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(getValue());
    }
}
