## コードの美しさ

**優れたコードは目に優しい**  
コードを読みやすくするための余白、配置、順序などのレイアウトについて  
  
  * 改行位置
  * 縦の整列
  * 段落分け
  * 順序
    * HTMLのinputフィールドと同じ並び
    * 重要度順
    * データベースのカラムの順番
  
以下のコードは、  
* 改行に一貫性がない
* 縦に整列していない
* 段落で分かれていない
* 順序が適切でない

```
public class PerformanceTester {
    public static final TcpConnectionSimulator cell = new TcpConnectionSimulator(
           500, /* Kbps */
           80, /* millisecs latency */
           200, /* jiter */
           1 /* packet loss % */);

    public static final TcpConnectionSimulator t3_fiber = 
       new TcpConnectionSimulator(
           45000, /* Kbps */
           10, /* millisecs latency */
           0, /* jiter */
           0 /* packet loss % */);
    public static final TcpConnectionSimulator wifi = new TcpConnectionSimulator(
           100, /* Kbps */
           400, /* millisecs latency */
           250, /* jiter */
           5 /* packet loss % */);
}
```
->

```
public class PerformanceTester {
    public static final TcpConnectionSimulator wifi =　　　 /* 重要な「wifi」を前に */  
    　　new TcpConnectionSimulator(                         /* 改行でnewの位置を揃える */ 
           500,   /* Kbps */                            　  /* インデントを整えて縦に並べる*/
           80,    /* millisecs latency */
           200,   /* jiter */
           1      /* packet loss % */);

    public static final TcpConnectionSimulator t3_fiber = 
       new TcpConnectionSimulator(
           45000, /* Kbps */
           10,    /* millisecs latency */
           0,     /* jiter */
           0      /* packet loss % */);
           　　　　　　　　　　　　　　                       /* 改行を入れて段落分け */
    public static final TcpConnectionSimulator cell =
    　　new TcpConnectionSimulator(
           100,   /* Kbps */
           400,   /* millisecs latency */
           250,   /* jiter */
           5      /* packet loss % */);
}
```

一貫性のある読み方が出来、読みやすい  
ただし同じコメントが三回も入れられているので  

```
public class PerformanceTester{
    // TcpConnectionSimulator(throughput, latency, jitter, packet_loss)
    //                            [Kbps]     [ms]    [ms]          [%]
    
    public static final TcpConnectionSimularor wifi =
      new TcpConnectionSimulator(500,   80,   200,   1);
      
    public static final TcpConnectionSimulator t3_fiber =
      new TcpConnectionSimulator(45000, 10,   0,     0);
    
    ...
}
```
としたほうが適切  

### Tips

* メソッドを使って整列させる  
* 関数や変数の宣言をブロックにまとめる
