package test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import net.arnx.jsonic.JSON;

import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class JunitJsonMap {

    // スタティックイニシャライザ
    static {
    }

    // インスタンスイニシャライザ
    {
    }

    /**
     * コンストラクタ。
     */
    public JunitJsonMap() {}

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {}

    @AfterClass
    public static void tearDownAfterClass() throws Exception {}

    @Before
    public void setUp() throws Exception {}

    @After
    public void tearDown() throws Exception {}

    @Test
    public void test_json_map_compare() {
        final String js1 = "{\"number\":10,\"string\":\"aaa\",\"array\":[1,2,3],"
                + " \"入れ子\": {\"子\": [{\"孫1\": 31}, {\"孫2\": 32}]}}";
        // js1 の内容を入れ替えたもの→異なる
        final String js2 = "{\"number\":10,\"string\":\"aaa\",\"array\":[1,2,3],"
                + " \"入れ子\": {\"子\": [{\"孫1\": 31}, {\"孫2\": 33}]}}";
        // js1 の JSON の順番を入れ替えたもの→同一
        final String js3 = "{\"number\":10,\"array\":[1,2,3],"
                + " \"入れ子\": {\"子\": [{\"孫1\": 31}, {\"孫2\": 32}]},\"string\":\"aaa\"}";
        // js1 の array の順番を入れ替えたもの→異なる
        final String js4 = "{\"number\":10,\"string\":\"aaa\",\"array\":[2,1,3],"
                + " \"入れ子\": {\"子\": [{\"孫1\": 31}, {\"孫2\": 32}]}}";

        Map<?, ?> j1 = JSON.decode(js1);
        Map<?, ?> j2 = JSON.decode(js2);
        Map<?, ?> j3 = JSON.decode(js3);
        Map<?, ?> j4 = JSON.decode(js4);

        System.out.println(j1);
        System.out.println(j2);
        System.out.println(j3);
        System.out.println(j4);

        // http://javazuki.blog.jp/archives/cat_1191115.html

        // is(): eaualsTo()のショートカット or Matcherをそのまま評価
        // not(): 評価の反転(否定)。is()の否定版
        assertThat(j1, not(j2));
        assertThat(j1, is(j3));
        assertThat(j1, not(j4));
        assertThat(j2, not(j3));
        assertThat(j2, not(j4));
        assertThat(j3, not(j4));

        assertThat(j1, not(equalTo(j2)));
        assertThat(j1, equalTo(j3));
        assertThat(j1, not(equalTo(j4)));
        assertThat(j2, not(equalTo(j3)));
        assertThat(j2, not(equalTo(j4)));
        assertThat(j3, not(equalTo(j4)));

        // sameInstance(): ｢==｣による検証
        assertThat(j1, not(sameInstance(j2)));
        assertThat(j1, not(sameInstance(j3)));
        assertThat(j1, not(sameInstance(j4)));
        assertThat(j2, not(sameInstance(j3)));
        assertThat(j2, not(sameInstance(j4)));
        assertThat(j3, not(sameInstance(j4)));

        assertNotEquals(j1, j2);
        assertEquals(j1, j3);
        assertNotEquals(j1, j4);
        assertNotEquals(j2, j3);
        assertNotEquals(j2, j4);
        assertNotEquals(j3, j4);

        assertEquals(false, j1.equals(j2));
        assertEquals(true, j1.equals(j3));
        assertEquals(false, j1.equals(j4));
        assertEquals(false, j2.equals(j3));
        assertEquals(false, j2.equals(j4));
        assertEquals(false, j3.equals(j4));

        assertEquals(false, j1 == j2);
        assertEquals(false, j1 == j3);
        assertEquals(false, j1 == j4);
        assertEquals(false, j2 == j3);
        assertEquals(false, j2 == j4);
        assertEquals(false, j3 == j4);

    }
}
