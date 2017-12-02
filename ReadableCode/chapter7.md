# 制御フローを読みやすくする
  
制御フローは出来るだけ「自然」にする。読み手が立ち止まったり、読み返したりしないように

## 条件式の変数
  条件式は　（調査対象）＝＝（比較対象）のように書く  
（変化する値）＝＝（変化しない値（基準））　とも言い換えられる  
  
## if/elseブロックの変数
  
* 否定形より肯定形
* 中身が短いほうが先（ifとelseが近くなる）
* 関心を引く条件を先に書く
  
などを考慮して優先度を考える  

## 三項演算子
  
結構一目では分かりにくいので、基本的にはif/elseを使う  
単純な場合（すぐにわかる場合）は三項演算子を使う  
``` time_str += (hour >= 12) ? "pm" : "am"```

## 関数では早めにReturnを使う
  
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
  
## ネストを浅くする
  
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

