# sip (Sınıf İçi Paylaşım uygulaması)
### Genel ; 
##### Sınıf İçi Paylaşım uygulaması Bilgisayar Mühendisliği Tasarım Çalışması(Proje)-1 olarak gerçekleştirdiğim projedir.Tasarımsal açıdan daha çok işlevselliğine önem verilmiştir.Aşağıda uygulamanın daha detaylı açıklamaları bulunmaktadır.
### Amacı;
##### Sınıf içi paylaşım uygulamasının amacı öğrenciler ve hocaları arasındaki iletişimi anlık olarak aktarmaktır.Bölüm web sitelerine veya OBS sistemine duyurular eklenir.Ancak bu duyurulardan anlık haberimiz olmaz.Öğrenci takip etmesi gereken sitelere herhangi bir sebepten geç girmesi durumunda bazı aksaklıklar ortaya çıkar.Bu uygulama sayesinde tüm duyuru ve diğer bilgilerden resim bazlı anlık olarak haberdar olabiliyoruz.
### Uygulama Akışı ; 
##### Uygulamamız splash ekran ardından giriş-kayıt ekranıyla bizi karşılıyor.TC , Mail adresi ve şifre giriliyor.TC kimlik numarası girmemizin sebebi okul öğrencisi olup olmadığını bu veri üzerinden kontrol ediyoruz.Gerekli veriler girildikten sonra öğrencinin fakülte,bölüm ve sınıf verileri alınıyor.Bu verilere göre önceden hazırlanmış veritabanlarına ekleniyor.Spinner ile seçimler yapıldıktan sonra bu bilgileri onaylama ekranı karşılıyor.Bu ekranıda geçtikten sonra chat/feed ekranına aktarıyor.Paylaşan kişinin adı,görsel ve açıklamaların yer aldığı bir feed ekranı var.Sağ üstteki menüden bizde eklemeler yapabiliyoruz.İlk defa ekleme yapıyorsak gerekli izinler isteniyor.Uygulamadan çıkılıp geri girildiğinde tekrardan kaldığı yerden devam edebiliyor.Gerçek zamanlı olarak veritabanı,depolama ve üyelik sistemleri Google Firebase üzerinden işleniyor.Uygulamanın kısaca genel akışı bu şekilde çalışmaktadır.
