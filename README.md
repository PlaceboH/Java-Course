Lab01 (03.03.2022)

Napisz aplikację, która pozwoli na sprawdzania wskazanych katalogów pod kątem wystąpienia zmian w zawartych w nich plikach. 

Aplikacja powinna wyliczać skrót MD5 dla każdego badanego pliku w dwóch krokach: 1. przygotowując "snapshot" bieżącej sytuacji oraz 2. weryfikując na podstawie zapamiętanego "snapshotu", czy w plikach wprowadzono jakieś zmiany.

Aplikacja powinna być też napisana z wykorzystaniem modułów (wprowadzonych w Javie od jdk 9). Powstać ma moduł biblioteki oraz moduł samej aplikacji (korzystający z modułu biblioteki). 

Powstałe moduły należy wyeksportować do plików jar.

Używając jlink należy przygotować minimalne środowisko uruchomieniowe, do którego podpięte zostaną wymienione wyżej moduły.

Aplikację powinno dać się uruchomić z linii komend, korzystając tylko z wygenerowanego środowiska uruchomieniowego. Sama aplikacja powinna oferować interfejs użytkownika (najlepiej graficzny, minimum - tekstowy).

Do operacji na plikach i katalogów należy wykorzystać pakiet klas java.nio. Ponadto zalecane jest napisanie programu w stylu funkcjonalnym.

Proszę dodać w aplikacji menadżer bezpieczeństwa. Proszę skorzystać z pliku polityki.


Lab02 (10.03.2022)

Napisz aplikację, która umożliwi przeglądanie danych osobowych zapisanych na dysku. Zakładamy, że dane osobowe zapisywane będą w folderach o nazwach odpowiadających identyfikatorom osób, których dotyczą. Dokładniej, w folderach pojawiać się mają dwa pliki: record.txt (o dowolnej strukturze, w pliku tym zapisane mają być: imię, nazwisko, wiek, ....) oraz image.png (ze zdjęciem danej osoby, przy czym do celów testowych można zamiast zdjęcia użyć dowolnego obrazka).

Interfejs graficzny tej aplikacji można zrealizować za pomocą dwóch paneli - jednego, przeznaczonego na listę przeglądanych folderów oraz drugiego, służącego do wyświetlania danych osobowych i zdjęcia odpowiedniego do folderu wybranego z listy.

Aplikację należy zaprojektować z wykorzystaniem słabych referencji (ang. weak references). Zakładamy, że podczas przeglądania folderów zawartość plików tekstowych i  plików zawierających obrazki będzie ładowana do odpowiedniej struktury. Słabe referencje powinny pozwolić na ominięcie konieczności wielokrotnego ładowania tej samej zawartości - co może nastąpić podczas poruszanie się wprzód i wstecz po liście folderów.

Aplikacja powinna wskazywać, czy zawartość pliku została załadowana ponownie, czy też została pobrana z pamięci. Wskazanie to może być zrealizowane za pomocą jakiegoś znacznika prezentowanego na interfejsie.

W celu oceny poprawności działania aplikację należy uruchamiać przekazując wirtualnej maszynie parametry ograniczające przydzielaną jej pamięć. Na przykład -Xms512m (co oznacza minimalnie 512 MB pamięci), -Xmx1024m (co oznacza maksymalnie 1GB).
Należy też przetestować możliwość regulowania zachowania się algorytmu odśmiecania, do czego przydają się opcje -XX:+ShrinkHeapInSteps, -XX:-ShrinkHeapInSteps. Proszę przestudiować, jakie inne atrybuty można przekazać do wirtualnej maszyny, w tym selekcji algorytmu -XX:+UseSerialGC, -XX:+UseParNewGC (deprecated), -XX:+UseParallelGC, -XX:+UseG1GC.

Architektura aplikacji powinna umożliwiać dołączanie różnych podglądaczy zawartości (czyli klas odpowiedzialnych za renderowanie zawartości plików z danymi), przy czym podglądacze powinny być konfigurowalne (np. poprzez określenie sposobu renderowania czcionek czy obrazków).

Proszę dodać do źródeł plik readme.md z wnioskami co do stosowalności opcji wirtualnej maszyny.

Proszę sięgnąć do materiałów http://tomasz.kubik.staff.iiar.pwr.wroc.pl/dydaktyka/Java/UnderstandingWeakReferences.pdf


Lab03 (17.03.2022)

Napisz aplikację, która pozwoli skonsumować dane pozyskiwane z serwisu oferującego publiczne restowe API. Ciekawą listę serwisów można znaleźć pod adresem:
https://rapidapi.com/collection/list-of-free-apis (wymagają klucza API), czy też https://mixedanalytics.com/blog/list-actually-free-open-no-auth-needed-apis/ (te klucza API nie wymagają). W szczególności w tej drugiej grupie istnieje: https://developers.teleport.org/api/reference/. Właśnie to api ma być użyte w aplikacji.

    Bazując na nim należy zbudować intefejs użytkownika, który pozwoli na przeprowadzanie testów z wiedzy z geograficznej. Renderowanie zapytań i odpowiedzi powinno być tak zaimplementowane, by dało się zmianić ustawienia językowe (lokalizacji) w oparciu o tzw. bundle (definiowane w plikach i klasach - obie te opcje należy przetestować). Wspierane mają być języki: polski i angielski. 

Proszę zapoznać się z api i zaproponować kilka schematów zapytań i pól odpowiedzi. Niech użytkownik ma możliwość parametryzowania tych zapytania (w miejsce kropek niech wpisywane będą wartości z list wyboru - jeśli da się je pozyskać z serwisu, lub niech będą to wartości wprowadzone wolnym tekstem) oraz ma możliwość zadeklarowanie własnej odpowiedzi. Odpowiedź podana przez użytkownika powinna być zweryfikowana przez aplikację (aplikacja, po wysłaniu zapytania przez api powinna sprawdzić, czy wynik tego zapytania jest zgodny z odpowiedzią udzieloną przez użytkownika).

Na przykład dla szablonu zapytania zapytania: 
    "Ile jednostek administracyjnych poziomu ... istnieje w państwie ...." (w miejsce kropek powinno dać się coś wpisać)
powinno istnieć jedno pole na wpisanie odpowiedzi
    "..."  (miejsce na wpisanie liczby).
oraz linijka weryfikacji, np.:
     "Tak, masz rację. W państwie .... istnieją 3 jednostki podziału administracyjnego poziomu ..." (to ma wypełnić sama aplikacja).

Jak widać na interfejsie użytkownika trzeba obsłużyć odmianę przez liczby. Można do tego zastosować wariantowe pobieranie tekstów z bundli. Do tego przyda się klasa ChoiceFormat. 

Do pozyskiwania danych być może trzeba będzie uruchomić kilka zapytań (patrz np. endpoint: https://api.teleport.org/api/countries/iso_alpha2:PL/admin1_divisions/)
W implementacji do wykorzystania są również klasy z pakietów java.text, java.util.

Proszę zuważyć, że we wskazanym api nie korzysta się z parametru pozwalającego określić język zwracanych danych (niektóre serwisy to umożliwiają, np. atrybutu languageCode jest używany w GeoDB Cities API: https://rapidapi.com/wirefreethought/api/geodb-cities?endpoint=59908d68e4b075a0d1d6d9ac ).

Opcjonalnie można wykorzystać jakieś inne api, jeśli tylko zachowa się przedstawioną wyżej koncepcję (parametryzowane szablony zapytań, do wypełnienia pola odpowiedzi, linijka weryfikacji z odmianą przez liczby/osoby - wszystko przynajmniej z obsługą dwóch języków: polski i angielski). Na przykład może powstać aplikacja testującą wiedzę o muzyce (ile dany kompozytor napisał jakich utworów).


Lab04 (24.03.2022)

Napisz aplikację, która umożliwi zlecanie wykonywania zadań instancjom klas ładowanym własnym ładowaczem klas. Do realizacji tego ćwiczenia należy użyć Java Reflection API.

Tworzona aplikacja powinna udostępniać graficzny interfejs, na którym będzie można:
1. zdefiniować zadanie (zakładamy, że będzie można definiować "dowolne" zadania reprezentowane przez ciąg znaków),
2. załadować klasę wykonującą zadanie (zakładamy, że będzie można załadować więcej niż jedną taką klasę),
3. zlecić wykonanie wskazanego zadania wskazanej załadowanej klasie, monitorować przebieg wykonywania zadania, wyświetlić wynik zadania.
4. wyładować wybraną klasę z wcześniej załadowanych

Realizacja zadania powinna opierać się na wykorzystaniu API (klas i interfejsów) zdefiniowanych w archiwum Refleksja02.zip.

Należy dostarczyć przynajmniej 3 różne klasy implementujące interfejs Processor. Każda taka klasa po załadowaniu powinna oznajmić, poprzez wynik metody getInfo(), jakiego typu zadanie obsługuje. Na przykład pozyskana informacja w postaci "sumowanie" oznaczałaby, że zadanie można zdefiniować ciągiem znaków "1 + 2", gdzie oczekiwanym wynikiem byłoby "3". Informacja "zamiana małych liter na duże" oznaczałaby, że dla zadania "abc" oczekiwanym wynikiem ma być "ABC".

Klasy ładowane powinny być skompilowane w innym projekcie niż sama użytkowa aplikacja (podczas budowania aplikacja nie powinna mieć dostępu do tych klas). Można założyć, że kod bajtowy tych klas będzie umieszczany w katalogu, do którego aplikacja będzie miała dostęp. Ścieżka do tego katalogu powinna być parametrem ustawianym w aplikacji w trakcie jej działania. Wartością domyślną dla ścieżki niech będzie katalog, w którym uruchomiono aplikację. Aplikacja powinna odczytać zawartość tego katalogu i załadować własnym ładowaczem odnalezione klasy. Zakładamy, że podczas działania aplikacji będzie można "dorzucić" nowe klasy do tego katalogu (należy więc pomyśleć o pewnego rodzaju "odświeżaniu").

Wybieranie klas (a tym samym algorytmów przetwarzania) powinno odbywać się poprzez listę wyświetlającą nazwy załadowanych klas. Nazwom tym niech towarzyszą opisy pozyskane metodą getInfo() z utworzonych instancji tych klas.

Zlecanie zadań instancjom klas powinno odbywać się poprzez metodę submitTask(String task, StatusListner sl).  W metodzie tej należy podać słuchacza typu StatusListener, który będzie otrzymywał informacje o zmianie statusu przetwarzania. Do reprezentacji statusu przetwarzania służy klasa Status (klasę tę zadeklarowano tak, aby po utworzeniu statusu jego atrybuty były tylko do odczytu). 

Proszę przetwarzanie zaimplementować w wątku z opóźnieniami, by dało się monitorować bieżący status przetwarzania.Do monitorowania statusów przetwarzania i wyświetlania wyników można użyć osobnej listy (monitora zadań) wyświetlanej na interfejsie aplikacji.

Proszę napisać własny (!) ładowacz klas. Ładowacz ten może być klasą, do której przekazana zostanie ścieżka położenia kodów bajtowych ładowanych klas z algorytmami przetwarzania. Własny ładowacz nie może rozszerzać klasy URLClassLoader.

Aplikację można stworzyć korzystając z jdk1.8. Można też spróbować zaimplementować ją korzystając z jdk11 (należy zastanowić się wtedy jednak, jak powinien wyglądać ładowacz klas).

Uwaga: klasa zostanie wyładowana, gdy znikną wszystkie referencje od jej instancji oraz gdy zniknie referencja do ładowacza, który tę klasę załadował (i zadziała odśmiecanie). Proszę monitorować liczbę załadowanych i wyładowanych klas za pomocą jconsole lub jmc.

Proszę zajrzeć do materiałów wymienionych przy wykładzie o refleksji i ładowaczach klas.


Lab05 (31.03.2022)

Zaimplementuj aplikację z graficznym interfejsem pozwalającą przeprowadzić analizę statystyczną na danych tabelarycznych.
Analiza ta może polegać na: wyznaczeniu linii trendu, obliczeniu mediany, obliczeniu odchylenia standardowego. 

Aplikacja ta powinna umożliwiać:
- wyświetlanie/edytowanie danych tabelarycznych;
- wybranie algorytmu, jakim będą one przetwarzane (należy zailplementować przynajmniej 2 algorytmy analiz statystycznych);
- wyświetlenie wyników przetwarzania.
W trakcie implementacji należy wykorzystać interfejs dostarczyciela serwisu (ang. Service Provider Interface, SPI).
Dokładniej, stosując podejście SPI należy zapewnić aplikacji możliwość załadowania klas implementujących zadany interfejs
już po zbudowaniu samej aplikacji. 
Klasy te (z zaimplementowanymi wybranymi algorytmami analizy skupień) mają być dostarczane w plikach jar umieszczanych w ścieżce. 
Należy stworzyć dwie wersje projektu: standardową oraz modularną.

Aby zapoznać się z problemem proszę sięgnąć do przykładowych projektów w archiwum udostępnionym pod adresem:
    http://tomasz.kubik.staff.iiar.pwr.wroc.pl/dydaktyka/Java/ServiceProviderInterfaceWorkspace.zip

W implementacji należy wykorzystać pakiet ex.api, zawierającym klasy o kodzie źródłowy pokazanym poniżej.

Trochę informacji o SPI można znaleźć pod adresem:
    https://www.baeldung.com/java-spi
Porównanie SPI ze SpringBoot DI zamieszczono pod adresem:
    https://itnext.io/serviceloader-the-built-in-di-framework-youve-probably-never-heard-of-1fa68a911f9b

--------------------------------
package ex.api;

/**
 * Interfejs serwisu pozwalającego przeprowadzić analizę statystyczną.
 * Zakładamy, że serwis działa asynchronicznie.
 * Na początek należy do serwisu załadować dane.
 * Potem można z serwisu pobrać wyniki analizy.
 * W przypadku niepowodzenia wykonania jakiejś metody wyrzucony zostanie wyjątek.
 * 
 * @author tkubik
 *
 */
public interface AnalysisService {
    // metoda ustawiająca opcje algorytmu (jeśli takowe są potrzebne)
	public void setOptions(String[] options) throws AnalysisException; 
	// metoda zwracająca nazwę algorytmu
	public String getName();                                   
	// metoda przekazująca dane do analizy, wyrzucająca wyjątek jeśli aktualnie trwa przetwarzanie danych
	public void submit(DataSet ds) throws AnalysisException; 
	// metoda pobierająca wynik analizy, zwracająca null jeśli trwa jeszcze przetwarzanie lub nie przekazano danych do analizy
	// wyrzucająca wyjątek - jeśli podczas przetwarzania doszło do jakichś błędów
	// clear = true - jeśli wyniki po pobraniu mają zniknąć z serwisu
    public DataSet retrieve(boolean clear) throws AnalysisException;   
}
--------------------------------

package ex.api;

public class AnalysisException extends Exception {
	private static final long serialVersionUID = 1L;
    AnalysisException(String msg){
    	super(msg);
    }
}

--------------------------------
package ex.api;
/**
 * Klasa reprezentująca zbiór danych w postaci tabelarycznej.
 * Przechowuje nagłówek (jednowymiarowa tablica z nazwami kolumn) 
 * oraz dane (dwuwymiarowa tablica, której wiersze reprezentują wektory danych).
 * Zakładamy, że będą zawsze istnieć przynajmniej dwie kolumny o nazwach:
 * "RecordId" - w kolumnie tej przechowywane są identyfikatory rekordów danych;
 * "CategoryId" - w kolumnie tej przechowywane są identyfikatory kadegorii rekordów danych (wynik analizy skupień).
*
 * @author tkubik
 *
 */
public class DataSet {
	private String[] header = {}; 
	private String[][] data = {{}};

	private <T> T[][] deepCopy(T[][] matrix) {
	    return java.util.Arrays.stream(matrix).map(el -> el.clone()).toArray(i -> matrix.clone());
	}
	
	public String[] getHeader() {
		return header;
	}
	public void setHeader(String[] header) {
		this.header = header.clone();
	}
	public String[][] getData() {
		return data;
	}
	public void setData(String[][] data) {
		this.data = deepCopy(data);
	}
}
--------------------------------

Lab06 (08.04.2022)

Zaimplementuj rozproszony system imitujący działanie sieci tablic reklamowych, na których cyklicznie wyświetlane są zadane teksty (tj. przez określony czas widać jedno hasło reklamowe, po czym następuje zmiana).
Wymiana danych pomiędzy elementami systemu powinna odbywać się poprzez gniazda SSL (z użyciem certyfikatów), z wykorzystaniem menadżera bezpieczeństwa i plików polityki.
(materiały do przestudiowania: 
 https://docs.oracle.com/javase/8/docs/technotes/guides/rmi/socketfactory/index.html
 https://docs.oracle.com/en/java/javase/11/security/java-secure-socket-extension-jsse-reference-guide.html
)

W systemie tym wyróżnione mają być trzy typy aplikacji (klas z metodą main):
* Manager (Menadżer) - odpowiedzialna za przyjmowanie od klientów zamówień wyświetlanie haseł reklamowych oraz przesyłanie tych haseł na tablice reklamowe
* Client (Klient) - odpowiedzialna za zgłaszanie menadżerowi zamówień lub ich wycofywanie
* Billboard (Tablica) - odpowiedzialna za wyświetlanie haseł, dowiązująca się do menadżera, który może zatrzymać i uruchomić wyświetlanie haseł

Podczas uruchomienia systemu należy utworzyć: 1 instancję Menadżera, przynajmniej 2 instancje Klienta, przynajmniej 3 instancje Tablicy. 
Muszą to być osobne aplikacje (nie mogą korzystać z tej samej przestrzeni adresowej!!!). Aplikacje powinny być parametryzowane na interfejsie lub w linii komend (by dało się je uruchomić na różnych komputerach).

Poniżej znajdują się kody interfejsów oraz kod klasy, które należy użyć we własnej implementacji. Kody te zawierają opisy, które powinny pomóc w zrozumieniu ich zastosowania.
 
Uwaga - Proszę uważać na niebezpieczeństwo konfliktu portów.
      - Proszę użyć dokładnie tego samego kodu co niżej (bez żadnych modyfikacji!!)

--------------------------------
////////////////////////////
package bilboards;

import java.io.Serializable;
import java.time.Duration; // available since JDK 1.8

/**
 * Klasa reprezentująca zamówienie wyświetlania ogłoszenia o zadanej treści
 * przez zadany czas ze wskazaniem na namiastke klienta, przez którą można
 * przesłać informacje o numerze zamówienia w przypadku jego przyjęcia
 * 
 * @author tkubik
 *
 */
public class Order implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String advertText;
	public Duration displayPeriod;
	public IClient client;
}
////////////////////////////
package bilboards;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.Duration;

/**
 * Interfejs, który powinna implementować aplikacja pełniąca rolę tablicy ogłoszeniowej (nazwijmy ją Tablica).
 * Aplikacja ta powinna wyświetlać cyklicznie teksty ogłoszeń dostarczane metodą addAdvertisement.
 * Za wyświetlanie tych ogłoszeń powinien odpowiadać osobny wątek.
 * Wątek powinien mieć dostęp do bufora na ogłoszenia, którego pojemność i liczbę wolnych miejsc
 * zwraca metoda getCapacity.
 * Za dodawanie ogłoszenia do bufora odpowiada metoda addAdvertisment. 
 * Z chwilą pierwszego wyświetlenia ogłoszenia na tablicy powinien zacząć zliczać się czas jego wyświetlania.
 * Usuwanie ogłoszenia może nastąpić z dwóch powodów: i) ogłoszenie może zostać usunięte na skutek
 * wywołania metody removeAdvertisement (przez Menadżera); ii) ogłoszenie może zostać usunięte, gdy skończy się przyznany 
 * mu czas wyświetlania na tablicy (przez wątek odpowiedzialny w Tablicy za cykliczne wyświetlanie testów).
 * Usuwanie ogłoszeń z bufora i ich dodawanie do bufora wiąże się z odpowiednim zarządzaniem
 * podległą strukturą danych
 * W "buforze" mogą się robić "dziury", bo ogłoszenia mogą mieć przyznany różny czas wyświetlania. 
 * Należy więc wybrać odpowiednią strukturę danych, która pozwoli zapomnieć o nieregularnym występowaniu tych "dziur").
 * Metoda start powinna dać sygnał aplikacji, że należy rozpocząć cykliczne wyświetlanie ogłoszeń.
 * Metoda stop zatrzymuje wyświetlanie ogłoszeń.
 * Metody start i stop można odpalać naprzemiennie, przy czym nie powinno to resetować zliczonych czasów wyświetlania 
 * poszczególnych ogłoszeń.
 * Uwaga: Tablica powininna być sparametryzowany numerem portu i hostem rejestru
 * rmi, w którym zarejestrowano namiastkę Menadżera, oraz nazwa, pod którą
 * zarejestrowano tę namiastkę.
 * Jest to potrzebne, by Tablica mogła dowiązać się do Menadżera. 
 * Tablica robi to wywołując metodę bindBillboard (przekazując jej swoją namiastkę typu IBillboard).
 * Otrzymuje przy tym identyfikator, który może użyć, by się mogła wypisać z Menadżera 
 * (co może stać się podczas zamykania tablicy).
 * 
 * @author tkubik
 *
 */

public interface IBillboard extends Remote {
	/**
	 * Metoda dodająca tekst ogłoszenia do tablicy ogłoszeniowej (wywoływana przez
	 * Menadżera po przyjęciu zamówienia od Klienta)
	 * 
	 * @param advertTextn   - tekst ogłoszenia, jakie ma pojawić się na tablicy
	 *                      ogłoszeniowej
	 * @param displayPeriod - czas wyświetlania ogłoszenia liczony od pierwszego
	 *                      jego ukazania się na tablicy ogłoszeniowej
	 * @param orderId       - numer ogłoszenia pod je zarejestrowano w menadżerze
	 *                      tablic ogłoszeniowych
	 * @return - zwraca true, jeśli udało się dodać ogłoszenie lub false w
	 *         przeciwnym wypadku (gdy tablica jest pełna)
	 * @throws RemoteException
	 */
	public boolean addAdvertisement(String advertText, Duration displayPeriod, int orderId) throws RemoteException;

	/**
	 * Metoda usuwająca ogłoszenie z tablicy (wywoływana przez Menadżera po
	 * wycofaniu zamówienia przez Klienta)
	 * 
	 * @param orderId - numer ogłoszenia pod jakim je zarejestrowano w menadżerze
	 *                tablic ogłoszeniowych
	 * @return - zwraca true, jeśli operacja się powiodła lub false w przeciwnym
	 *         wypadku (gdy nie ma ogłoszenia o podanym numerze)
	 * @throws RemoteException
	 */
	public boolean removeAdvertisement(int orderId) throws RemoteException;

	/**
	 * Metoda pobierająca informację o zajętości tablicy (wywoływana przez
	 * Menadżera)
	 * 
	 * @return - zwraca tablicę dwóch liczb całkowitych - pierwsza to pojemność
	 *         bufora tablicy, druga to liczba wolnych w nim miejsc
	 * @throws RemoteException
	 */
	public int[] getCapacity() throws RemoteException;

	/**
	 * Metoda pozwalająca ustawić czas prezentacji danego tekstu ogłoszenia na
	 * tablicy w jednym cyklu (wywoływana przez Menadżera). Proszę nie mylić tego z
	 * czasem, przez jaki ma być wyświetlany sam tekst ogłoszenia. Prezentacja
	 * danego hasła musi być powtórzona cyklicznie tyle razy, aby sumaryczny czas
	 * prezentacji był równy lub większy zamówionemu czasowi wyświetlania tekstu
	 * ogłoszenia.
	 * 
	 * @param displayInterval - definiuje czas, po którym następuje zmiana hasła
	 *                        wyświetlanego na tablicy. Odwrotność tego parametru
	 *                        można interpetować jako częstotliwość zmian pola
	 *                        reklamowego na Tablicy.
	 * @throws RemoteException
	 */
	public void setDisplayInterval(Duration displayInterval) throws RemoteException;

	/**
	 * Metoda startująca cykliczne wyświetlanie ogłoszeń (wywoływana przez
	 * Menadżera)
	 * 
	 * @return - zwraca true, jeśli zostanie poprawnie wykonana lub false w
	 *         przeciwnym wypadku
	 * @throws RemoteException
	 */
	public boolean start() throws RemoteException;

	/**
	 * Metoda stopująca cykliczne wyświetlanie ogłoszeń (wywoływana przez Menadżera)
	 * 
	 * @return - zwraca true, jeśli zostanie poprawnie wykonana lub false w
	 *         przeciwnym wypadku
	 * @throws RemoteException
	 */
	public boolean stop() throws RemoteException;
}
////////////////////////////
package bilboards;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interfejs, który powinien zaimplementować klient (nazwijmy tę alikację
 * Klient) komunikujący się z menadżerem tablic. Klient powinien mieć interfejs
 * pozwalający: i) definiować zamówienia wyświetlania ogłoszenia (tekst
 * ogłoszenia, czas wyświetlania) ii) składać zamówienia wyświetlania ogłoszenia
 * Menadżerowi, iii) wycofywać złożone zamówienia.
 * 
 * Przy okazji składania zamówienia wyświetlania ogłoszenia Klient przekazuje
 * Menadżerowi namiastkę IClient. Menagdżer użyje tej namiastki, by zwrotnie
 * przekazać klientowi numer zamówienia (jeśli oczywiście zamówienie zostanie
 * przyjęte). Ma to działać podobnie jak ValueSetInterface z przykładu RMITest.
 * 
 * Numery zamówień i treści ogłoszeń przyjętych przez Menadżera powinny być
 * widoczne na interfejsie Klienta. Klient powinien sam zadbać o usuwanie
 * wpisów, których okres wyświetlania zakończył się (brak synchronizacji w tym
 * względzie z menadżerem)
 * 
 * Uwaga: Klient powinien być sparametryzowany numerem portu i hostem rejestru
 * rmi, w którym zarejestrowano namiastkę Menadżera, oraz nazwa, pod którą
 * zarejestrowano tę namiastkę.
 * 
 * @author tkubik
 *
 */
public interface IClient extends Remote { // host, port, nazwa
	/**
	 * Metoda służąca do przekazania numeru przyjętego zamówienia (wywoływana przez
	 * Menadżera na namiastce klienta przekazanej w zamówieniu)
	 * 
	 * @param orderId
	 * @throws RemoteException
	 */
	public void setOrderId(int orderId) throws RemoteException;
}
////////////////////////////
package bilboards;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interfejs, który powinna implementować aplikacja pełniąca rolę menadżera
 * tablic (nazwijmy ją Menadżer). Menadżer powinien wyświetlać wszystkie
 * dowiązane do niego tablice oraz ich bieżący stan. Tablice dowiązują się do i
 * odwiązują od menadżera wywołując jego metody bindBillboard oraz
 * unbindBillboard. Z menadżerem może połączyć się Klient przekazując mu
 * zamówienie wyświetlania danego ogłoszenia przez zadany czas. Robi to
 * wywołując metodę placeOrder. Menadżer, jeśli przyjmie zamówienie, zwraca
 * Klientowi numer zamówienia wykorzystując przy tym przekazaną w zamówieniu
 * namiastke. Klient rozpoznaje, czy przyjęto zamówienie, sprawdzając wynik
 * zwracany z metody placeOrder.
 * Zamówienia natychmiast po przyjęciu trafiają na dowiązane Tablice mogące w danej chwili przyjąć ogłoszenie do wyświetlania.
 * Jeśli w danej chwili nie ma żadnej wolnej Tablicy zamówienie nie powinno być przyjęte do realizacji.
 * Aby przekonać się o stanie tablic Menadżer wywołuje metody ich namiastek getCapacity.
 * Wystarczy, że istnieje jedna wolna tablica by przyjąć zamówienie.
 * Na ile tablic trafi dane zamówienie decyduje dostępność wolnych miejsc w chwili zamówienia.  
 * 
 * Uwaga: Menadżer powinien utworzyć lub połączyć się z rejestrem rmi o
 * wskazanym numerze portu. Zakładamy, że rejestr rmi działa na tym samym
 * komputerze, co Menadżer (może być częścią aplikacji Menadżera).
 * Menadżer rejestruje w rejestrze rmi posiadaną
 * namiastke IManager pod zadaną nazwą (nazwa ta nie może być na twardo
 * zakodowanym ciągiem znaków). Nazwa namiastki menadżera, host i port na którym
 * działa rejest rmi powinny być dostarczone Klientowi (jako parmetry) oraz Tablicom.
 * 
 * @author tkubik
 *
 */
public interface IManager extends Remote { // port, nazwa, GUI

	/**
	 * Metoda dowiązywania namiastki Tablicy do Menadżera (wywoływana przez Tablicę)
	 * 
	 * @param billboard - dowiązywana namiastka
	 * @return - zwraca numer przyznany namiastce w Menadżerze
	 * @throws RemoteException
	 */
	public int bindBillboard(IBillboard billboard) throws RemoteException;

	/**
	 * Metoda odwiązująca namiastkę Tablicy z Menadżera (wywoływana przez Tablicę)
	 * @param billboardId - numer odwiązywanej namiastki
	 * @return
	 * @throws RemoteException
	 */
	public boolean unbindBillboard(int billboardId) throws RemoteException;

	/**
	 * Metoda służąca do składania zamówienia wyświetlania ogłoszenia (wywoływana przez Klienta)
	 * @param order - szczegóły zamówienia (wraz z tekstem ogłoszenia, czasem jego wyświetlania i namiastką klienta)
	 * @return - zwraca true jeśli przyjęto zamówienie oraz false w przeciwnym wypadku
	 * @throws RemoteException
	 */
	public boolean placeOrder(Order order) throws RemoteException;

	/**
	 * Metoda służąca do wycofywania zamówienia (wywoływana przez Klienta)
	 * @param orderId - numer wycofywanego zamówienia 
	 * @return - zwraca true jeśli wycofano zamówienie oraz false w przeciwnym wypadku
	 * @throws RemoteException
	 */
	public boolean withdrawOrder(int orderId) throws RemoteException;
}
////////////////////////////
--------------------------------

Lab07 (13.04.2022)

Napisz program, który pozwoli zasymulować działanie narzędzia do obsługi finansów komitetu rodzicielskiego jakiejś szkolnej klasy. Główną funkcją tego narzędzia ma być obsługa przypadku zbierania funduszy na organizowane wydarzenia. Narzędzie może działać jako aplikacja desktopowa w trybie konsolowym, może też być zaimplementowane jako aplikacja z graficznym interfejsem (desktopowa lub internetowa, ale to wymagałoby więcej pracy).

Zadanie należy zrealizować wykorzystując relacyjną bazę danych. Można skorzystać z sqlite czy też h2 (zalecane, bo nie ma potrzeby instalowania żadnego dodatkowego serwisu) lub innej bazy danych (pod warunkiem, że podczas oddawania zadania będzie można połączyć się z tą bazą danych).

Podczas realizacji zadania można skorzystać z wzorca projektowego DAO (oraz możliwości, jakie daje JDBC) lub mapowania ORM (oraz możliwości, jakie daje JPA razem z frameworkiem Hibernate).

W przypadku użycia DAO (z JDBC) proszę pamiętać, by parametryzować zapytania SQL (nie wolno budować zapytań poprzez "sklejanie" kolejnych ciągów znaków). Proszę pamiętać o przewijalności zbioru wynikowego.

W przypadku zastosowania mapowania ORM proszę zadbać o automatyczne wygenerowanie schematu bazy danych oraz zastosowanie warstwy serwisów.

Zakładamy, że w bazie danych będą przechowywane następujące informacje:
* Wydarzenie - identyfikator, nazwa, miejsce, termin
* Osoba - identyfikator, imię, nazwisko
* Raty - identyfikator, identyfikator wydarzenia, numer raty, termin płatności, kwota?
* Wpłaty - identyfikator, termin wpłaty, kwota wpłaty, identyfikator osoby, identyfikator wydarzenia, numer raty
 
Program powinien:
* umożliwiać ręczne wprowadzanie danych (osób, wydarzeń, rat, wpłat) oraz ładowanie danych z plików csv.
* umożliwiać przeglądanie danych (w szczególności przeglądanie należnych i dokonanych wpłat)
* automatycznie sprawdzać terminowość i wysokość wpłat oraz wysyłać monity o kolejnych płatnościach (wystarczy, że będzie pisał do pliku z logami monitów, upływ czasu należy zasymulować).
* automatycznie eskalować monity w przypadku braku terminowej wpłaty (wystarczy, że będzie pisał do pliku z logami ekalowanych monitów, upływ czasu należy zasymulować)


Lab08 (21.04.2020)

Zaimplementuj serwis komunikujący się protokołem SOAP, który pozwoli wykorzystać logikę biznesową (z warstwą persystencji) zaprojektowaną w ramach lab07. Serwis ten ma udostępniać API pozwalające na zarządzanie wydarzeniami, osobami, ratami, wpłatami. Klientem serwisu może być narzędzie SoapUI lub Postman (nie trzeba samemu implementować żadnego klienta).

Podczas implementacji można wykorzystać dowolną metodę przetwarzania komunikatów SOAP.
Z uwagi na dużą automatyzację zalecane jest wykorzystanie JAX-WS/Apache CXF. Można wybrać podejście bottomUp lub topDown.
Zalecane jest oparcie budowanego rozwiązania na czymś, co pozwoli obsłużyć opis interfejsu w języku WSDL.

Tworzony projekt może być projektem eclipsowym (DynamicWebProject) lub jeszcze lepiej - projektem mavenowym.

Istnieje niezły tutorial o tym, jak stworzyć projekt mavenowy, który korzysta z CXF (https://www.cse.unsw.edu.au/~cs9322/labs/lab01/index.html).
Jest też (może nawet lepszy) tutorial na stronie: https://www.tutorialspoint.com/apache_cxf/apache_cxf_with_wsdl_first.htm

Można też spróbować zrealizować laboratorium korzystając z Spring Boot (odpowiedni tutorial znajduje się pod adresem: https://www.baeldung.com/spring-boot-soap-web-service).
    

Lab09 (28.04.2022)

Zaimplementuj aplikację pozwalającą na szyfrowanie/deszyfrowanie plików (taka aplikacja mogłaby pełnić rolę narzędzia służącego do szyfrowania/odszyfrowywania załączników do e-maili). 
Na interfejsie graficznym aplikacji użytkownik powinien mieć możliwość wskazania plików wejściowych oraz wyjściowych, jak również algorytmu szyfrowania/deszyfrowania oraz wykorzystywanych kluczy: prywatnego (do szyfrowania) i publicznego (do deszyfrowania).
Cała logika związana z szyfrowaniem/deszyfrowaniem powinna być dostarczona w osobnej bibliotece, spakowanej do podpisanego cyfrowo pliku jar.
Sama zaś aplikacja powinna również być wyeksportowana do wykonywalnego pliku jar podpisanego cyfrowo (i działać w niej ma menadżer bezpieczeństwa korzystający z dostarczonego pliku polityki).
Projekt opierać ma się na technogiach należących do Java Cryptography Architecture (JCA) i/lub Java Cryptography Extension (JCE).
Proszę zwrócić uwagę na ograniczenia związane z rozmiarem szyfrowanych danych narzuczane przez wybrane algorytmu (zależy nam, by zaszyfrować dało się pliki o dowolnym rozmiarze).
W trakcie realizacji laboratorium będzie trzeba skorzystać z repozytoriów kluczy i certyfikatów.  Ponadto proszę zapoznać się z zasadami korzystania z narzędzia jarsigner. 

Proszę w gitowym repozytorium kodu w gałęzi sources/releases stworzyć osobne podkatalogi: na bibliotekę (biblioteka) oraz na aplikację (aplikacja).


Lab10 (05.05.2022)

Bazując na kodzie stworzonym w trakcie poprzednich laboratoriów przygotuj: a) wielowydaniowy jar, b) instalator aplikacji. Opis czym jest wielowydaniowy jar pojawi się na wykładzie. Towarzyszące mu materiały znaleźć można na stronie poświęconej kursowi. Instalator aplikacji może być wykonany dowolnym narzędziem. Ponadto dla ciekawych polecane jest sprawdzenie, jak działa narzędzie jpackage. 

Wyniki proszę dostarczyć w gitowym repozytorium lab10. W poprzednich latach należało dostarczyć również raport z realizacji zadania (zamieszczany w repozytorium). Podobnie w tym roku proszę taki raport przygotować (wymaganie to pojawiło się z opóźnieniem, proszę postarać się je wypełnić).
     
Lab11 (12.05.2022)

Napisz program, w którym wykorzystane zostanie JNI. Zadanie do wykonania polegać ma na zadeklarowaniu klasy oferującej trzy metody natywne pozwalające posortować wskazane tablice obiektów typu Double. Schemat implementacji tej klasy powinien odpowiadać poniższemu wzorcowi.

class .....{
.....
public Double[] a;
public Double[] b;
public Boolean order;


public native Double[] sort01(Double[] a, Boolean order);
// zakładamy, że po stronie kodu natywnego będzie sortowana przekazana tablica a 
// (order=true oznacza rosnąco, order=false oznacza malejąco)
// metoda powinna zwrócić posortowaną tablicę
public native Double[] sort02(Double[] a); 
// zakładamy, że drugi atrybut będzie pobrany z obiektu przekazanego do metody natywnej (czyli będzie brana wartość pole order) 
public native void sort03();
// zakładamy, że po stronie natywnej utworzone zostanie okienko pozwalające zdefiniować zawartość tablicy do sortowania 
// oraz warunek określający sposób sortowania order.
// wczytana tablica powinna zostać przekazana do obiektu Javy na pole a, zaś warunek sortowania powinien zostać przekazany
// do pola orded
// Wynik sortowania (tablica b w obiekcie Java) powinna wyliczać metoda Javy multi04 
// (korzystająca z parametrów a i order, wstawiająca wynik do b).

public void sort04(){
 ..... // sortuje a według order, a wynik wpisuje do b
 }

}


Lab12 (19.05.2022)

Celem laboratorium jest przetrenowanie możliwości dynamicznego rozszerzania funkcji programu Java przez ładowanie i wyładowywanie skryptów JavaScript (na podobieństwo ładowania klas własnym ładowaczem). Ponadto chodzi w nim o opanowanie technik przekazywania obiektów pomiędzy wirtualną maszyną Java a silnikiem JavaScript.

Rozwój silnika JavaScript, odbywający się w ramach rozwoju JDK, został w pewnym momencie zatrzymany przez Oracle. Stwierdzono bowiem, że zadanie to realizowane jest w projekcie GrallVM, a szkoda poświęcać zasoby na powielanie prac. Efekt podjętej wtedy decyzji widać w JDK 11, gdzie odpowiedni moduł dostarczający rzeczony silnik otagowano:
    @Deprecated(since="11", forRemoval=true)
    Module jdk.scripting.nashorn

Wraz z tym modułem z JDK z kolejnych wydań JDK zniknąć mają niektóre pomocnicze narzędzia, jak uruchamiane z linii komend interpreter jjs. Póki co można jednak wciąż korzystać z silnika JavaScript w JDK 11. Można też, alternatywnie, pobrać i przećwiczyć środowisko uruchomieniowe GrallVM (patrz https://www.graalvm.org/downloads/). 

GraalVM Community Edition wersja 22.1.0 (obecnie najnowsza) zbudowano na bazie OpenJDK 11.0.15 oraz OpenJDK 17.0.3. Odpowiednią paczkę oprogramowania znaleźć można na stronie: https://github.com/graalvm/graalvm-ce-builds/releases/tag/vm-22.1.0

Zadanie do wykonania polega na napisaniu programu w języku Java pozwalającego na wizualizację działania automatów komórkowych, przy czym logika działania tych automatów powinna być zaimplementowana za pomocą ładowanych dynamicznie skryptów JavaScript zapisanych w plikach o znanej lokalizacji. Nazwy plików ze skryptami powinny odpowiadać nazwom automatów - by było wiadomo, co robi ładowany skrypt. Załadowane skrypty powinno dać się wyładować.

Opis przykładowych automatów komórkowych opublikowano na wiki: https://pl.wikipedia.org/wiki/Automat_kom%C3%B3rkowy.
Materiały pomocnicze można znaleźć pod adresami:
http://www.oracle.com/technetwork/articles/java/jf14-nashorn-2126515.html
https://www.n-k.de/riding-the-nashorn/

Proszę w gitowym repozytorium kodu w gałęzi sources umieścić wszystkie wykorzystywane artefakty (skrypty JavaScript oraz źródła kodu Java). Proszę też zamieścić instrukcję użycia programu wraz z udokumentowanym wynikiem jego działania (plik Readme.md z dołączomymi zrzutami z ekranu).


Lab13 (26.05.2022)

Znając zasady korzystania ze skryptów JS w programach napisanych w języku Java można pójść o krok dalej w zabawie z tymi technologiami. Zdobytą wiedzę można wykorzystać podczas budowy aplikacji z graficznym interfejsem użytkownika opierającym się na klasach JavaFX.

Zadanie polega na zaimplementowaniu aplikacji korzystającej z JavaFX w niestandardowy sposób. Ma to polegać na oskryptowaniu całej logiki w pliku fxml zamiast w kontrolerze napisanym w języku Java. W języku Java ma być zaimplementowane tylko ładowanie fxmla. Proszę spojrzeć na przykłady zamieszczone poniżej (Sample1.fxml, Sample1.java, application.css). 

Sama aplikacja ma pozwalać na generowanie zaproszeń na różnego rodzaju towarzyskie imprezy (parapetówki, andrzejki, sylwestra, rocznice ...) na podstawie spreparowanych wcześniej szablonów uzupełnianych niezbędnymi atrybutami (datą, miejscem, imieniem, ...). Wygenerowane zaproszenia mogą pojawiać się na interfejsie użytkownika jako tekst, który powinno dać się skopiować. Szablony powinny być zapisane w plikach konfiguracyjnych.

Do przemyślenia jest, w jaki sposób użytkownik ma przekazywać do aplikacji niezbędne atrybuty (liczba i typ atrybutów może zależeć od rodzaju szablonu).

W gitowym repozytorium w gałęzi sources należy umieścić wszystkie źródła plus plik Readme.md (z dołączonymi zrzutami i opisem, jak uruchomić aplikację), w gałęzi releases - wykonywalny plik jar (z przygotowaniem jara może być problem - trzeba sprawdzić, czy pliki fxml, css oraz szablonów będą się ładować z tego jara, ostatecznie można je dystrybuować osobno).

########### Sample1.fxml  ###########
<?xml version="1.0" encoding="UTF-8"?>
<?language javascript?>
<?import javafx.scene.control.*?>
<?import javafx.scene.layout.*?>

<VBox fx:id="vbox" layoutX="10.0" layoutY="10.0" prefHeight="250.0"
	prefWidth="300.0" spacing="10" xmlns:fx="http://javafx.com/fxml/1"
	xmlns="http://javafx.com/javafx/2.2">
	
	<fx:script>
	var System = Java.type("java.lang.System")
		function buttonAction(event){
		java.lang.System.out.println("finally we get to print something.");
		outputLbl.setText("Your Input please:");
		}
	</fx:script>
	<children>

		<Label fx:id="inputLbl" alignment="CENTER_LEFT" cache="true"
			cacheHint="SCALE" prefHeight="30.0" prefWidth="200.0"
			text="Please insert Your Input here:" textAlignment="LEFT" />
		<TextField fx:id="inputText" prefWidth="100.0" />
		<Button fx:id="okBtn" alignment="CENTER_RIGHT"
			contentDisplay="CENTER" mnemonicParsing="false"
			onAction="buttonAction(event);" text="OK" textAlignment="CENTER" />

		<Label fx:id="outputLbl" alignment="CENTER_LEFT" cache="true"
			cacheHint="SCALE" prefHeight="30.0" prefWidth="200.0"
			text="Your Input:" textAlignment="LEFT" />
		<TextArea fx:id="outputText" prefHeight="100.0"
			prefWidth="200.0" wrapText="true" />
	</children>
</VBox>

########### Sample1.java  ###########
package application;

import java.io.FileInputStream;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Sample1 extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		String fxmlDocPath = "src/Sample1.fxml";
		FileInputStream fxmlStream = new FileInputStream(fxmlDocPath);

		VBox root = (VBox) loader.load(fxmlStream);

		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene);
		stage.setTitle("A FXML Example without any Controller");
		stage.show();

	}
}
########### application.css  ###########
/* JavaFX CSS - Leave this comment until you have at least create one rule which uses -fx-Property */
.pane {
    -fx-background-color: #1d1d1d;
}

.root {
		-fx-padding: 10;
		-fx-border-style: solid inside;
		-fx-border-width: 2;
		-fx-border-insets: 5;
		-fx-border-radius: 5;
		-fx-border-color: #2e8b57;
		-fx-background-color: #1d1d1d;
}
.label {
    -fx-font-size: 11pt;
    -fx-font-family: "Segoe UI Semibold";
    -fx-text-fill: white;
    -fx-opacity: 0.6;
}

.label-bright {
    -fx-font-size: 11pt;
    -fx-font-family: "Segoe UI Semibold";
    -fx-text-fill: white;
    -fx-opacity: 1;
}

.label-header {
    -fx-font-size: 32pt;
    -fx-font-family: "Segoe UI Light";
    -fx-text-fill: white;
    -fx-opacity: 1;
}

.text-field {
    -fx-font-size: 12pt;
    -fx-font-family: "Segoe UI Semibold";
}

.button {
    -fx-padding: 5 22 5 22;   
    -fx-border-color: #e2e2e2;
    -fx-border-width: 2;
    -fx-background-radius: 0;
    -fx-background-color: #1d1d1d;
    -fx-font-family: "Segoe UI", Helvetica, Arial, sans-serif;
    -fx-font-size: 11pt;
    -fx-text-fill: #d8d8d8;
    -fx-background-insets: 0 0 0 0, 0, 1, 2;
}

.button:hover {
    -fx-background-color: #3a3a3a;
}

.button:pressed, .button:default:hover:pressed {
  -fx-background-color: white;
  -fx-text-fill: #1d1d1d;
}

.button:focused {
    -fx-border-color: white, white;
    -fx-border-width: 1, 1;
    -fx-border-style: solid, segments(1, 1);
    -fx-border-radius: 0, 0;
    -fx-border-insets: 1 1 1 1, 0;
}

.button:disabled, .button:default:disabled {
    -fx-opacity: 0.4;
    -fx-background-color: #1d1d1d;
    -fx-text-fill: white;
}

.button:default {
    -fx-background-color: -fx-focus-color;
    -fx-text-fill: #ffffff;
}

.button:default:hover {
    -fx-background-color: derive(-fx-focus-color,30%);
}

Lab14 (02.06.2022)

Napisz program, który będzie grał rolę sterownik zapalającego światła w pomieszczeniach budynku (np. ramka z kilkoma kółkami reprezentującymi punkty oświetlenia). Zadaniem sterownika jest symulowanie "aktywności" podczas nieobecności gospodarzy (czyli odpalanie świateł w jakichś sekwencjach).
Zanurz w tym programie agenta obsługującego ziarenko JMX, które pozwala na zmianę sekwencji, uruchamianie lub zatrzymywanie symulacji, bądź też na indywidualne włączane/wyłączanie świateł. Ziarenko powinno generować notyfikacje przy włączeniu/wyłączeniu każdego punktu oświetlenia.
Niech więc ziareno ma:
- właściwość (pozwalającą na ustawianie/odczytywanie sekwencji),
- metodę (włączającą/wyłączającą dane źródło światła),
- metodę (uruchamiającą/zatrzymującą symulację)
- notyfikację (generowaną przy włączeniu/wyłączeniu punktów oświetlenia - i to zarówno przez użytkownika jak i przez ziarenko).
Uruchom program oraz podłącz się do niego z jmc lub jconsole by wykorzystać agenta.


Lab15 (09.06.2022)

Ostatnie zadanie w semestrze będzie tylko na zaliczenie. W jego trakcie należy przećwiczyć posługiwanie się "dużymi typami numerycznymi" z pakietu java.math.
Przypadkiem roboczym ma być całkowanie dowolnej ciągłej funkcji metodą parabol (Simpsona). Opis algorytmu oraz przykład jego implementacji w arkuszu kalkulacyjnym można znaleźć, odpowiednio, pod adresami:
http://www.kipo.agh.edu.pl/data/NumInt.pdf
http://vistula.pk.edu.pl/~sciezor/Kurs_TI_XP/Excel2_lekcja_3.pdf

Dokładniej: 
- proszę zaimplementować metodę całkowania Simpsona w dwóch wersjach: bez oraz z użyciem "dużych typów numerycznych" (BigDecimal, BigInteger itp.).
- proszę uruchomić całkowanie wybranej funkcji f(x) obiema metodami (testowaną funkcję należy sobie zaimplementować, może to być funkcja wielomianowa, wykładnicza itp.)
- proszę porównać wyniki całkowania i wyciągnąć wnioski
- opcjonalnie częściowe wyniki całkowania można w dowolny sposób wyświetlić na wykresie (np. obliczone całki częściowe można zrzucić do pliku, a następnie wyświetlić wykres scałkowanej funcji jakimś narzędziem)
