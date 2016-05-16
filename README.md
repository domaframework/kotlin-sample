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
#### パラメータ名の取得について

JSR 269の注釈処理ではパラメータの名前を取得できるのですが、Kotlinのkaptには不具合があり取得できません。

- [ KT-9609 Kapt: Class constructor arguments lose names and become arg0, arg1, etc](https://youtrack.jetbrains.com/issue/KT-9609)
 
対応策として、エンティティクラスのコンストラクタやDaoインタフェースのメソッドでは `@ParameterName` を使ってパラメータに名前をつけてください。

#### エラーメッセージについて
JSR 269の注釈処理と異なり、Kotlinのkaptは問題があるソースコードの場所を示しません。
Domaのメッセージの末尾の文言（`at className.memberName`）を参考にしてください。

```sh
エラー: [DOMA4005] @Selectや@Updateなど問い合わせの種別を表すアノテーションが必要です。 at sample.PersonDao.insert
エラー1個
```

Domaのメッセージそのものがエラーの発生場所を示していることもあります。
