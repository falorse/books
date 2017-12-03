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

## ハノイの塔

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
