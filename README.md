Doma Kotlin Sample
------------------

### Build

```sh
./gradlew clean build
```

### Run

```sh
./gradlew clean run
```

----

### 注意点

#### エラーメッセージについて
JSR 269の注釈処理と異なり、Kotlinのkaptは問題があるソースコードの場所を示しません。
Domaのメッセージの末尾の文言（`at className.memberName`）を参考にしてください。

```sh
エラー: [DOMA4005] @Selectや@Updateなど問い合わせの種別を表すアノテーションが必要です。 at sample.PersonDao.insert
エラー1個
```

Domaのメッセージそのものがエラーの発生場所を示していることもあります。

### ドキュメント

- [Kotlin サポート](http://doma.readthedocs.io/ja/stable/kotlin-support/)

