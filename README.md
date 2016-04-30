Doma Kotlin Sample
------------------

### Build

```sh
./gradlew --info clean build
```

### Run

```sh
./gradlew --info clean run
```

----
### 注意点
#### パラメータ名の取得について

JSR 269の注釈処理ではパラメータの名前を取得できるのですが、Kotlinのkaptには不具合があり取得できません。

- [ KT-9609 Kapt: Class constructor arguments lose names and become arg0, arg1, etc](https://youtrack.jetbrains.com/issue/KT-9609)
 
対応策として、エンティティクラスのコンストラクタやDaoインタフェースのメソッドでは `@ParameterName` を使ってパラメータに名前をつけてください。



