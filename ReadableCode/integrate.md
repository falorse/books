リーダブルコードのまとめ  
初学者に向けた資料として  

# 理解しやすいコード

優れたコードの特徴はいろいろある  
  
* 簡潔である
* 短い
* 実行時間・消費メモリが小さい
* テストしやすい
* 理解しやすい
  
この中では、実行時間と短さなど、競合するものも多々あるが、  
最も重要なのが  
  
* **理解しやすい** 

という点  
常に一歩下がって、「（他人や数か月後の自分にとって）このコードは理解しやすいだろうか」と自問自答すべき

# 表面上の改善（一行ごとの改善）

## 名前に情報を詰め込む

**名前は短いコメントである**    

名前に情報を詰め込むことで、情報を多く伝えて理解されやすくする  
いい名前は、変数の目的や値を表す  
  
* 明確な単語を選ぶ
* 汎用的な名前を避ける
* 具体的な名前を選ぶ
* 接尾辞や接頭辞を使って情報を付加する
* 命名規則
  
### 明確な単語を選ぶ

**複数の意味に取れる単語は避ける**  
  
```
class Tree{
	void size();
}
```

単に```size()```だけでは、何のサイズなのかが分からない  
```height()```、```memSize()```、```numNodes()```などとすれば何のサイズを取得しているのかわかりやすい  

```Stop()```は、停止することは分かるがその後に再起動できるのかどうかが不明である  
```Kill()```、```Pause()```ならばその情報も付加することが出来る  

### 汎用的な名前を避ける

**何の意味もない名前は避ける**

tmp、retval(raturn value)、foo、aなど、意味のない汎用的な名前は避ける  

## 具体的な名前を使う  

**変数や関数などの名前は、抽象的なものでなく出来る限り具体的にする**  

```--run_locally```という、ローカル環境でデバッグをするためにデバッグログを抽出して出力する目的で使っていたオプションがある  
ローカル環境で使うのでこのままでも間違ってはいないが、知らない人はローカルで使う時必ず入れるものだと勘違いしてしまう  

  
```--debug_locally```とすればデバッグするときに用いるという情報を付加できる  
更に、```--export_dubug_log_locally```のようにすればこのオプションが具体的に何をするのかという情報まで付加できる  

### 接尾辞や接頭辞を使って情報を付加する

**知らせなけらばいけない情報は変数名に付加する**  

表示する前にエスケープしなければならないコメント  
```comment``` -> ```unescaped_comment```  
  
暗号化して扱うべきだけどまだプレインテキストなパスワード  
```password``` -> ```unencripted_password```や```plaintext_password```  

### 命名規則  

RubyやRuby on Rails固有の命名規則もあるので参照すべし  
  
https://qiita.com/gakkie/items/3afcd505c786364aa5fa  
https://qiita.com/takahashim/items/ccfd489c9b26f15b7193
  
bool型を返す関数はRubyは他と違い、  
末尾に?を付ける  
isやhasも付けない  
```is_user``` -> ```user?```  

### Tips

* スコープの大きい変数には長い名前を、短い変数には短い名前を
* 大文字やアンダースコアにも意味を含める
* 長すぎないように


## 誤解されない名前

**「他の意味と間違えられることはないだろうか」と自問自答する**  
誤解されないか想像しながら名前を決める  
複数の候補を検討して名前を決める  
**最善の名前は、誤解されない名前**である 
  
* あいまいな単語は避ける
  * 限界値を含むときはmin,maxを使う
  * 範囲を指定するときはfirst,lastを使う
  * 包含/排他的範囲にはbegin,endを使う
  * ブール値の名前
* 期待に合わせる

### あいまいな単語は避ける

**英単語にはあいまいなものが多い**  
多義語の方がすぐに思いつくので選びがちでもある  

```
results = Database.all_objects.filter("year <= 2011");
```

```filter```という単語があいまいなので、```result```に2011年以前と2011年より後のどっちが含まれているのかあいまいである  
```exclude```や```select```を使うことでハッキリする  

#### 限界値を含む時はmin,maxを使う

```cart_too_big_limit = 10```  
では、10を超えたらエラーなのか10でもエラーなのかあいまいである  

```max_items_in_cart = 10```  
とすれば、10個がセーフなのがハッキリする  

#### 範囲を指定する時はfirst,lastを使う

```print integer_range(start = 2, stop = 4)```  
->  
```print integer_range(first = 2, last = 4)```  

#### 包含/排他的範囲にはbegin,endを使う

```print events_in_range(begin = "OCT 16 12:00am", end = "OCT 17 12:00am")```

上と合わせて慣例的な命名規則っぽい

#### bool値の名前

**bool値や、bool値を返す関数の名前を選ぶときは、trueとfalseの意味を明確にする**

```read_password? = true```  
では、これから読む必要があるのか、すでに読んだのかあいまい  

```need_password?```や、```authenticated?```などの方が適切  
また、bool値は否定形より肯定形の方が分かりやすい  

### 期待に合わせる

```get()```は、単にメンバ関数を取得するために使われることが多いので、軽い動作が期待される  
```getAverage()```と命名しつつ実際にはO(n)などの計算を行って返していると、  
予期しない実行時間の増加につながりかねないので、  
```calcAverage()```  
などが適切  


## コードの美しさ

**優れたコードは目に優しい**  
コードを読みやすくするための余白、配置、順序などのレイアウトについて  
以下の点に注意する  

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

## コメント

**コメントは、書き手の意図を読み手に知らせるもの**  

コードの動作を説明するためだけでない  
あくまでも「コメント」なので短く簡潔にするよう工夫する  

### コメントすべきではないこと  
  
* コードから十分に読み取れること
* コードを修正することで伝えられること
  
  
### コメントすべきこと  
  
* コードから読み取れない・分かりにくいこと
  * なぜコードが他のやり方でなくこうなっているのか
  * コードの欠陥（TODOなど）
  * そのコード、定数にまつわる背景
  * コードの意図
* 全体像（要約）
* 知らないと困る・ハマリそうなこと
* 質問されそうなこと

# ループとロジックの改善（一ブロックの改善）

## 制御フローを読みやすくする
  
制御フローは出来るだけ「自然」にする。読み手が立ち止まったり、読み返したりしないように
  
* 条件式の変数
* if/elseブロックの変数
* 三項演算子
* 関数では早めにReturn
* ネストを浅くする


### 条件式の変数
  条件式は　（調査対象）＝＝（比較対象）のように書く  
（変化する値）＝＝（変化しない値（基準））　とも言い換えられる  
  
### if/elseブロックの変数
  
* 否定形より肯定形
* 中身が短いほうが先（ifとelseが近くなる）
* 関心を引く条件を先に書く
  
などを考慮して優先度を考える  

### 三項演算子
  
結構一目では分かりにくいので、基本的にはif/elseを使う  
単純な場合（すぐにわかる場合）は三項演算子を使う  
``` time_str += (hour >= 12) ? "pm" : "am"```

### 関数では早めにReturn
  
特に、特定の条件などは一番上にまとめておく  
ガード節と言われる  

```
public void method() {
    if (よくあるケース/正常なケース) {
        // 通常の処理
    } else if (特殊なケース1) {
        // 特殊な処理1
    } else if (特殊なケース2) {
        // 特殊な処理2
    } else {
        // それ以外の処理
    }
}
```

→

```
public void method() {
    if (特殊なケース1) {
        // 特殊な処理1
        return;
    }

    if (特殊なケース2) {
        // 特殊な処理2
        return;
    }

    // 通常の処理
}

```
  
### ネストを浅くする
  
ifやforをできる限り重ねないように書く  
早めにReturnしたりメソッドで切り分けたりすると浅くなるかも  

```
public int method(){
	if( current_user.id == 1 ){
		// 処理1
		if ( current_user.status == 1){
			// 処理2
		}
		// 処理3
		return 1;
	}else{
		return 2;
	}
}
```

→

```
public int method(){
	if( current_user.id != 1){
		return 2;
	}
	
	// 処理1
	if( current_user.status == 1){
		// 処理2
	}
	// 処理3
	return 1;
}
```

## 巨大な式を分割する
コードの複雑な式を簡略化する

* 説明変数、要約変数
* ド・モルガンの法則
* メソッド化
  
### 説明変数、要約変数
  
1. 大きなコードの塊  
2. 一目では分かりにくい塊  
3. 何回か使う塊  (DRY)  
  
は、分かりやすい名前の変数に置き換える  

```
if (line.split(':')[0].strip() == "root"){
	//
}
```

->

```
username = line.split(':')[0].strip()
if username == "root"
	//
```
  
```
if(request.user.id == document.owner_id){
	//
}else{
	//
}
```

->

```
boolean user_own_document = (request.user.id == document.owner.id)
if(user_own_document){
	//
}

if(!user_own_document){
	//
}
```


### ド・モルガンの法則
  
論理式を簡潔に書くための工夫  

```
!(A && !B)
!A || B
は等価
```

論理式の中の　! や　() は極力減らしていく  
でもunless とか if not は個人的には嫌い  

### メソッド化
  
以下のような場合はメソッドに切り出せるかも  
メソッドに切り出す際は、分かりやすい名前にしておく  
  
* メインの処理でない場合
* 複雑な条件処理
* 道中は複雑だけど結果は単純な処理
  

```
if( int(status/100) == 4 || int(status/100) == 5 || err != null) {
	//
} 
```

->

```
if( hasError()){
	//
}
```

## 変数と読みやすさ
変数を適当に使うと、プログラムが理解しにくくなる  

* いらない変数を削除する
* 変数のスコープを縮める
* 変数は出来る限り変更しない
  

### いらない変数を削除する
  
以下のような変数は削除できる可能性があるので注意すべき  
  
	• 役に立たない変数
		○ 複雑な処理を分割していない
		○ 分かりにくい処理を分かりやすくする変数でない
		○ 重複する処理を一度にまとめるものでない
	• 中間結果を保持するためだけのもの（工夫すれば削除できるかも）
	• 制御フロー変数（done = false; -> ループ中にtrueに変更されるような変数)

### 変数のスコープを縮める

スコープが長いと、予想外の変更、追いにくい変更が出てくる  
スコープを短くして気にしなければいけない変数は減らしたい  
グローバル変数やセッションに保存する変数も出来れば少なくしたい  

```
PaymentInfo info = database.ReadPaymentInfo();
if(info){
	// info を使う処理
}

// infoを使わない処理
```

->

```
if(PaymentInfo info = database.ReadPaymentInfo()){
	// infoを使う処理
}

// infoを使わない処理
```

### 変数は出来る限り変更しない

変数の変更が多いと、値を追跡することが難しくなる  
constやfinalを使うと一か所見ればわかるので変数の値がわかりやすくなる  

# コードの再構成（全体の改善）

## 無関係の下位問題を抽出する
  
**大きな問題から、小さな問題を抽出してメソッド化する**  

以下のような問題は積極的に分割する  
* ライブラリにあるような汎用的な問題
* 複数のコントローラーで使える問題（ApplicationControllerに書くべき問題）
* 複雑な処理をまとめて分かりやすい名前に置き換えられる問題
* 単に元のメソッドが長すぎる場合

その部分のユニットテストによってデバッグがしやすくなるメリットもある  
ただし、分割しすぎても読みにくいので、程よい長さに（10行～20行程度？）  


```
// 与えられた緯度経度に最も近い'array'の要素を返す。
// 地球が完全な球体であることを前提としている。
var findClosestLocation = function (lat, lng, array) {
    var closest;
    var closest_dist = Number.MAX_VALUE;
    for (var i = 0; i < array.length; i += 1) {
        // 2つの地点をラジアンに変換する。
        var lat_rad = radians(lat);
        var lng_rad = radians(lng);
        var lat2_rad = radians(array[i].latitude);
        var lng2_rad = radians(array[i].longitude);

        // 「球面三角法の第二余弦定理」の公式を使う。
        var dist = Math.acos(Math.sin(lat_rad) * Math.sin(lat2_rad) +
                             Math.cos(lat_rad) * Math.cos(lat2_rad) *
                             Math.cos(lng2_rad - lng_rad));
        if (dist < closest_dist) {
            closest = array[i];
            closest_dist = dist;
        }
    }
    return closest;
};
```

->

```
var findClosestLocation = function (lat, lng, array) {
    var closest;
    var closest_dist = Number.MAX_VALUE;
    for (var i = 0; i < array.length; i += 1) {

	// 分かりにくい計算の中身を一行で代替出来る
        var dist = spherical_distance(lat, lng, array[i].latitude, array[i].longitude);

        if (dist < closest_dist) {
            closest = array[i];
            closest_dist = dist;
        }
    }
    return closest;
};

// この部分だけでのユニットテストも出来るようになる
var spherical_distance = function (lat1, lng1, lat2, lng2) {
    var lat1_rad = radians(lat1);
    var lng1_rad = radians(lng1);
    var lat2_rad = radians(lat2);
    var lng2_rad = radians(lng2);
    // 「球面三角法の第二余弦定理」の公式を使う。
    return Math.acos(Math.sin(lat1_rad) * Math.sin(lat2_rad) +
                     Math.cos(lat1_rad) * Math.cos(lat2_rad) *
                     Math.cos(lng2_rad - lng1_rad));
};
```
## 一度に一つのことを
  
メソッドは一つずつのタスクを行うようにする  
以下のように分解する    

1. コードが行っている「タスク」を列挙する  
2. 「タスク」を一つ一つの簡易な関数、領域に分割する  
  
例えば、標準偏差を計算したい場合  

1. 平均値を計算  
2. 分散を計算  
3. 標準偏差を計算  

となる  
（元の問題からして短めなのであまりいい例ではない）  

```
// 標準偏差を計算したい
public double calcStandatdDiviation(int[] data){
        double sum = 0;
        double vars = 0;
        for(int i=0; i<data.length; i++ ) {
            sum += data[i];
        }
        double ave = ( (double)sum )/data.length;
        for (int i=0; i<data.length; i++) {
            vars += ((data[i] - ave)*(data[i] - ave));
        }
       double std = Math.sqrt(vars/data.length);
       return std;
}
```

->  

```
public double calcStandatdDiviation(int[] data){

        double ave = calcAverage(data);
	
	double vars = calcVariance(data, ave);

       double std = Math.sqrt(vars/data.length);
 
      return std;
}
```

## コードを説明してみる

コードをより簡潔、簡単にするテクニック  
やりたいことを簡単な言葉で説明し、その説明に合わせてコードを書くようにする  
「簡単な言葉で説明する」ということは、コードの整理だけでなくデバッグにも役立つ（ラバーダッキング）  


### ロジックを説明してみる

ユーザーに閲覧権限がない場合にトップページに戻す機能を実装しているとして…  

```
$is_admin = is_admin_request();
if($document){
	if(!is_admin && ($document['username'] != $_SESSION['usernanme'])){
		return not_authorized();
	}
}else{
	if(!$is_admin){
		return not_authorized();
	}
}

// レンダリング
```

この機能は、  
ドキュメントが存在して、  
  管理者でない　かつ　ドキュメントを持つユーザーがセッションのユーザーでない  
	  
もしくは  
  
ドキュメントが存在せず、  
  管理者でない  
	  
場合にトップページに戻す  

要するに  
管理者である　もしくは　ドキュメントが存在し、セッションのユーザーがその所有者である  
の場合にレンダリングするので、  

```
if($is_admin){
	// 権限あり
}elseif($document && ($document['username'] == $_SESSION['username'])){
	// 権限あり
}else{
	return not_authorized();
}

// レンダリング
```

と書ける  

### ハノイの塔

ハノイの塔を三つのスタックを用いて解く  
  
ハノイの塔の解き方は、  
「n枚を左から右へ移動させる」  
ことである  
  
これを少し詳しく説明すると  
  
1. 左からn-1枚を右を中継して真ん中に  
2. 左から一枚を右に  
3. 真ん中からn-1枚を左を中継して右に  
  
というアルゴリズムになる  
  
n-1枚を移動させる問題もまた、  
1. 左からn-2枚を真ん中を中継して右に
2. 左から1枚を真ん中に
3. 右からn-2枚を左を中継して真ん中に
  
と書ける  

n枚をAからBを中継してCへ  
という処理が共通なので、  
```moveDiscs(int n, Stack A, Stack B, Stack C);```  
というメソッドが抽出できる  
  
上の説明をそのままコードにして、  
（実際にはコメントは多分いらない）  

```
    static boolean solveHanoiTower(int n){
        Stack[] stacks = new Stack[3];
        for(int i = 0; i < 3; i++){
            stacks[i] = new Stack();
        }
        
        for(int i = n; i > 0; i--){
            stacks[0].push(i);
        }
        
	// n枚を左から真ん中を中継して右へ移動させる
        moveDisks(n,stacks[0],stacks[1],stacks[2]);
        
	// 合ってるかチェック
        if(stacks[0].isEmpty() && stacks[1].isEmpty())
            return true;
        
        return false;
    }
    
    static void moveDisks(int n, Stack origin, Stack buffer, Stack dest){
        if( n <= 0)
            return;
        
	//  originからn-1枚をdestを中継してbufferに
        moveDisks(n - 1, origin, dest, buffer);
        
	// originから一枚をdestに
        dest.push(origin.pop());
        
	// bufferからn-1枚をoriginを中継してdestに
        moveDisks(n - 1, buffer, origin, dest);
    }
```

## コードを小さく保つ

コードの短さ　≒　分かりやすさ、保守しやすさ  
**コードは出来る限り小さく、軽量に維持する**  

* YAGNI
* 身近なライブラリ、プラグインに親しむ
* 不必要なコメント、メソッドなどは消す
  
### YAGNI(You ain't gonna need it)

現段階では必要ない機能は追加しない  
YAGNI とは、機能は実際に必要となるまでは追加しないのがよいとする、エクストリーム・プログラミングにおける原則である  

「後で使うだろうという予測の元に作ったものは、実際には10%程度しか使われない。」らしいので、必要になった時に実装するようにする  

### 身近なライプラリ、プラグインに親しむ

標準ライブラリにどんなものがあるかチェックしてみるといい  
割と使いそうだけど実装するのが面倒なものは大概誰かが作ってくれているものである    

### 不必要なコメント、機能は消す

p文とか以前のコードなどのコメントアウトは消して、意味のあるコメントだけを残す  
使わなくなった機能のコードは消す  
  
昔のコードを確認したくなったらソースコード管理システムを使う  




