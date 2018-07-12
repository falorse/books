
# リファクタリングチェックシート

リファクタリングする際やコードを書く・見直す際に気を付けることを列挙  

## 一行ごと
* [ ] 変数・メソッドの名前は具体的かつ明確で、複数の意味に取れないものか？
* [ ] 変数・メソッドの内容・返り値は名前から予測できるものか？
* [ ] 汎用的すぎる名前を使っていないか？（iとかj以外で)
* [ ] 命名規則に則っているか？
* [ ] 改行・縦のラインなどのレイアウトは読みやすくなっているか？
* [ ] コメントは必要かつ適切かつ簡潔か？
## ブロックごと
* [ ] 制御フローの変数は自然か？
* [ ] ネストは浅くできないか？
* [ ] ブロック節や早めのReturnを使えないか？
* [ ] 説明変数・要約変数で分かりやすく出来る文は残ってないか？
* [ ] 不要な中間変数・制御変数は残っていないか？
* [ ] 変数のスコープは適切か？
## メソッドごと
* [ ] 汎用的な部分問題はないか？
* [ ] 切り出せる部分問題はないか？
* [ ] メソッドは長すぎないか？短すぎないか？
* [ ] 一つのメソッド内に複数のタスクが含まれていないか？
## 全体的に
* [ ] 処理を簡潔な言葉で説明できるか？
* [ ] 不必要なメソッド・機能はないか？