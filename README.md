# Travail théorique sur le Bluetooth Low Energy (DAA)



**Auteurs:** Timothée Van Hove, Léo Zmoos

**Date:** 15.01.2024



## Introduction (Tim)

Brève explication de Bluetooth Low Energy. Comparaison avec le Bluetooth classique.



## Historique (Léo)
Anciennement connu sous le nom de Wibree, puis devenu la marque déposée Bluetooth Smart.

Le Bluetooth Low Energy(BLE) est une technologie de transmission sans fil créée par Nokia en 2006.

Cette technologie vise à répondre aux besoins croissants d'applications sans fil à faible consommation d'énergie.
(Exemples: capteurs, montres connectée, dispositifs médicaux, domotique etc.)

le Bluetooth Low Energy est omniprésent dans de nombreux dispositifs du quotidien. 

Il permet de faciliter la communication entre les appareils de manière économe en énergie.

En conclusion, il joue donc un rôle central dans l'évolution de l'Internet des objets (IoT).

## Concepts de base (Léo)

### GATT (Generic Attribute Profile)

Le GATT (Generic Attribute Profile) est l'un des piliers fondamentaux du Bluetooth Low Energy (BLE). 

Il permet de définir la manière dont les dispositifs communiquent et échangent des données. 

Le GATT repose sur un modèle client-serveur:

#### Client
C'est un périphérique qui initie et contrôle les connexions avec le serveur. (Ex. smartphone)

#### Serveur
Le serveur contient des données regroupées sous le nom de services. 

Chaque service contient des caractéristiques qui sont les informations pouvant être échangées.

## Services

Les services sont des conteneurs qui regroupent des données et fonctions liées entre elle (voir exemple ci-dessous) sur un appareil Bluetooth. 

Par exemple:

> Pour un dispositif de suivi d'activité physique. Il y aurait un service dédié à la surveillance du rythme cardiaque.


Ils aident donc à organiser les données et fonctions de manière ordonnée. 

Chaque conteneur possède un UUID(Identifiant Universel Unique) et peut contenir une ou plusieurs  caractéristiques à l'intérieur.

### Caractéristiques

Les caractéristiques représentent les données échangeables au sein d'un service.

#### Propriétés:
- Associée à un type de données spécifique(int, strings, etc.) 
- Possèdes son propre UUID. 

Les caractéristiques jouent un rôle crucial dans la définition des fonctionnalités offertes par un périphérique BLE.


### Modes de fonctionnement du BLE : Central et Périphérique
#### Mode Central

Dans le mode central, un dispositif joue le rôle de "centrale" et s'occupe d'initier la connexion avec un ou plusieurs périphériques. 


> Généralement, ce sont des dispositifs tels que les smartphones/tablettes ou ordinateurs qui utiliseront ce mode.

Ces derniers contrôlent et collectent des données à partir de périphériques BLE.


#### Mode Périphérique

Le mode périphérique correspond au rôle des dispositifs qui se rendent disponibles pour la connexion. 

Ces dispositifs émettent des données ou fournissent des services à d'autres dispositifs BLE en mode ***central***.

Les périphériques BLE sont par exemple:
- des capteurs
- des objets portables
- Autres appareils émettant des données à des fins spécifiques.

#### Les deux modes:

Ensemble, ces deux modes permettent des interactions flexibles entre les dispositifs BLE.

## BLE vs Bluetooth classic (Léo)

Le Bluetooth Low Energy (BLE) et le Bluetooth classique représentent deux évolutions distinctes de la technologie sans fil Bluetooth, chacune adaptée à des besoins spécifiques.

Nous allons donc comparer ces deux technologies sur quelques points pertinents:

### Consommation d'énergie:

L'une des comparaisons majeures que l'on peut faire entre ces deux technologie est évidemment dans l'utilisation de l'énergie.

Comme vu auparavant, le BLE est conçu pour limiter au maximum la consommation énergétique. C'est donc pour ça qu'il est autant adapté à l'IOT.

A contrario, le bluetooth classique permettra d'effectuer d'autres actions qui nécessiterons plus d'énergie.

### Portée:

**BLE:**
- Offre une portée suffisante pour l'IOT ou domotique par exemple mais plus courte que le bluetooth classique
- Cette portée plus courte s'explique par le fait qu'on essaie de limiter au maximum la consommation d'énergie

**Bluetooth Classic:**
- Offre une plus grande portée que le BLE
- Utile pour des applications nécessitant des communications sur des plus longues distances


### Débit de données:
**BLE:**
- Optimisé pour un débit de données plus bas, toujours en lien avec la consommation énergétique
- Adaptés aux petites quantités de données transmises fréquemment

**Bluetooth Classic:**
- Permet un débit de données plus élevé
- Adapté aux applications nécessitant le transfert rapide de volumes importants de données


### Applications:
Comme vu auparavant, les applications de ces technologie sont donc bien différentes.

**BLE**: IOT, Domotique, etc.
**Bluetooth Classic**: Haut-parleurs, casques, connexions entre smartphones et ordinateurs, etc.

En conclusion, il n'y en un pas un qui serait "mieux" que l'autre, ils ont juste un objectif différent!



## Implémentation dans Android

L'implémentation d'une communication BLE dans Android est complexe. Elle nécessite plusieurs étapes:

1. La gestion des permissions
2. La détection du device
3. la connexion au device (GATT server)
4. Le transfert de données entre les devices

Nous vous proposons une application de démo sur notre [repo Github](https://github.com/DrC0okie/HEIG_DAA_Workshop), qui implémente uniquement la détection de devices BLE et la gestion des permissions. L'implémentation des 2 autres fonctionnalités nécessiterait plus de temps dans le cadre de ce travail.

### Permissions Bluetooth

Les applications utilisant le Bluetooth doivent déclarer des permissions spécifiques dans le manifest de l'application, avec des exigences variant selon la version cible du SDK Android.

#### Pour Android 12 ou supérieur

- Utiliser la permission `BLUETOOTH_SCAN` pour rechercher des appareils Bluetooth, comme les périphériques BLE.
- Utiliser la permission `BLUETOOTH_ADVERTISE` si l'application rend l'appareil courant découvrable par d'autres devices Bluetooth.
- Utiliser la permission `BLUETOOTH_CONNECT` pour communiquer avec des appareils Bluetooth déjà appairés.
- Déclarer `ACCESS_FINE_LOCATION` si l'application utilise les résultats de la recherche Bluetooth pour déterminer la position physique.
- Les permissions `BLUETOOTH_ADVERTISE`, `BLUETOOTH_CONNECT` et `BLUETOOTH_SCAN` sont des permissions d'exécution, nécessitant l'approbation de l'utilisateur.

#### Pour Android 11 ou inférieur

- Utiliser la permission `BLUETOOTH` pour toute communication via Bluetooth classique ou BLE.
- Déclarer `ACCESS_FINE_LOCATION` car une recherche Bluetooth pourrait potentiellement être utilisée pour recueillir des informations sur la localisation de l'utilisateur.

#### Découverte des appareils Bluetooth locaux

- Déclarer la permission `BLUETOOTH_ADMIN` pour permettre à l'application d'initier la découverte d'appareils ou de manipuler les paramètres Bluetooth.

### Spécification de l'utilisation des fonctionnalités Bluetooth

- Ajouter des indicateurs dans le manifest indiquant si le Bluetooth est une composante essentielle de l'application, en utilisant l'élément `<uses-feature>`. Par exemple: `android.hardware.bluetooth` pour le Bluetooth classique et `android.hardware.bluetooth_le` pour le BLE.
- Pour rendre l'application disponible sur des appareils ne supportant pas le Bluetooth classique ou le BLE, incure l'élément `<uses-feature>` dans le manifest avec `required="false"`. Ensuite, vérifier la disponibilité des fonctionnalités au runtime en utilisant `PackageManager.hasSystemFeature()`

#### Sommaire:

```xml
    <!--BLUETOOTH PERMISSIONS-->
    <!-- Request legacy Bluetooth permissions on older devices. -->
    <uses-permission android:name="android.permission.BLUETOOTH" />
    <uses-permission android:name="android.permission.BLUETOOTH_ADMIN" />

    <!-- Needed only if the app looks for Bluetooth devices.
             If the app doesn't use Bluetooth scan results to derive physical
             location information, we can strongly assert that the app
             doesn't derive physical location. -->
    <uses-permission android:name="android.permission.BLUETOOTH_SCAN" />

    <!-- Needed only if the app makes the device discoverable to Bluetooth devices. -->
    <uses-permission android:name="android.permission.BLUETOOTH_ADVERTISE" />

    <!-- Needed only if the app communicates with already-paired Bluetooth devices. -->
    <uses-permission android:name="android.permission.BLUETOOTH_CONNECT" />

    <!--hardware option: required="false" if we check it on runtime-->
    <uses-feature android:name="android.hardware.bluetooth_le" android:required="false"/>
```



### Recherche des appareils BLE ([doc](https://developer.android.com/develop/connectivity/bluetooth/ble/find-ble-devices))

Pour trouver des appareils BLE, il faut utiliser la méthode startScan(). Cette méthode prend un ScanCallback en paramètre. Il faut  implémenter ce callback, car c'est ainsi que les résultats du scan sont renvoyés. Le scan est gourmand en batterie, il faut respecter les bonnes pratiques suivantes :

* Dans le cas où on cherche un device précis, dès que nous l'avons trouvé, arrêter le scan.
* Dans le cas où nous ne connaissons pas l'appareil à connecter, il ne faut jamais faire un scan en boucle. Toujours fixer une limite de temps au scan. 
* Un appareil qui était disponible auparavant peut s'être déplacé hors de portée, et le fait de continuer à scanner épuise la batterie.

Pour rechercher uniquement certains types de périphériques, on peut appeler `startScan(List<ScanFilter>, ScanSettings, ScanCallback)`, en fournissant une liste d'objets `ScanFilter` qui limitent les périphériques recherchés par l'analyse et un objet `ScanSettings` qui spécifie les paramètres de l'analyse.

Dans l'exemple suivant, l'application BLE fournit une activité pour rechercher les appareils Bluetooth LE disponibles et les afficher dans une RecyclerView. Ce exemple comprend la vérification des permissions au runtime:

```kotlin
class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: LeDeviceListAdapter
    private lateinit var scanCallback: ScanCallback
    private lateinit var progressBar: ProgressBar
    private var devices = mutableListOf<BleDevice>()
    private val granted = PackageManager.PERMISSION_GRANTED
    private val accessLoc = android.Manifest.permission.ACCESS_FINE_LOCATION
    private val REQUEST_PERMISSIONS_CODE = 1

    // Get the bluetooth manager
    private val bluetoothAdapter: BluetoothAdapter? by lazy {
        val bluetoothManager = getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        bluetoothManager.adapter
    }

    // Runtime checked permissions
    private val requiredPermissions = arrayOf(
        accessLoc,
        android.Manifest.permission.BLUETOOTH_SCAN,
        android.Manifest.permission.BLUETOOTH_CONNECT
    )

    private val bleScanner: BluetoothLeScanner?
        get() = bluetoothAdapter?.bluetoothLeScanner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // initialize the RecyclerView with the adapter and the progress bar
        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.adapter = LeDeviceListAdapter(devices)
        recyclerView.layoutManager = LinearLayoutManager(this)
        progressBar = findViewById(R.id.progress_bar)
        
        // Click listener for the "Scan" button
        findViewById<Button>(R.id.button_refresh).setOnClickListener {
            devices.clear()
            adapter.notifyDataSetChanged()
            startBleScan()
        }
    }
}
```



La méthode `startBleScan` est utilisée pour démarrer le processus de scan des devices BLE à proximité. On commence par vérifier si toutes les permissions sont accordées. Ça inclut la permission d'accès à la localisation (`ACCESS_FINE_LOCATION`), qui est requise pour la détection des périphériques BLE. Si les permissions ne sont pas accordées, la méthode demande ces permissions à l'utilisateur. Si les permissions sont accordées, un `ScanCallback` est défini. Il contient plusieurs méthodes qui traitent les différents événements du processus de scan:

- `onScanResult` : Appelée à chaque fois qu'un périphérique BLE est détecté. Extrait les informations du périphérique (comme le nom et l'adresse) et met à jour l'adapter de la RecyclerView.
- `onBatchScanResults` : Utilisée pour gérer un groupe de résultats de scan disponibles en une seule fois, bien que dans notre cas, elle n'est pas implémentée.
- `onScanFailed` : Appelée en cas d'échec du processus de scan, permettant de gérer les erreurs.

Après la définition du callback, la méthode affiche une barre de progression pour indiquer à l'utilisateur que le scan est en cours, puis démarre le scan en appelant `startScan` sur l'objet `bleScanner`.

Finalement, un délai est défini pour arrêter le scan après 10 secondes, pour économiser la batterie et limiter les ressources utilisées, en utilisant `Handler().postDelayed` qui appelle la méthode `stopBleScan` après un délai de 10 secondes.

```kotlin
private fun startBleScan() {
    if (requiredPermissions.any { checkSelfPermission(it) != granted }) {
        // Request permissions, then start scan
        requestPermissions(requiredPermissions, REQUEST_PERMISSIONS_CODE)
    } else {
        // Permissions granted, define callback, then start scan
        val scanCallback = object : ScanCallback() {
            override fun onScanResult(callbackType: Int, result: ScanResult) {

                if (checkSelfPermission(accessLoc) != granted) {
                    // Handle the case where permission is not granted
                    return
                }

                val device = result.device
                val bleDevice = BleDevice(device.name ?: "Unknown", device.address)
                if (bleDevice !in devices) {
                    adapter.devices.add(bleDevice)
                    adapter.notifyItemInserted(adapter.devices.size - 1)
                }
            }
            // List of scan results that are previously scanned.
            override fun onBatchScanResults(results: MutableList<ScanResult>) {}
            
            // Handle scan failure
            override fun onScanFailed(errorCode: Int) {}
        }
        // Show progress bar
        progressBar.visibility = View.VISIBLE

        // Start the scan
        bleScanner?.startScan(scanCallback)

        // Stop the scan after 10 seconds
        Handler(Looper.getMainLooper()).postDelayed({ stopBleScan()}, 10000) // 10 seconds
    }
}
```



La méthode stopBleScan arrête simplement le scan en cours et fait disparaître la `ProgressBar`

```kotlin
private fun stopBleScan() {
    if (::scanCallback.isInitialized && checkSelfPermission(accessLoc) == granted) {
        bleScanner?.stopScan(scanCallback)
    }
    // Hide the ProgressBar
    progressBar.visibility = View.GONE
}
```



L'override de la méthode `onRequestPermissionsResult` est appelée après que l'utilisateur ait répondu à une demande de permission avec le code de la requête (`requestCode`), un tableau des permissions demandées (`permissions`) et un tableau des résultats (`grantResults`).

On commence par vérifier le `requestCode` pour s'assurer qu'il correspond au code de demande de permissions envoyé . Dans ce cas, `REQUEST_PERMISSIONS_CODE` est utilisé comme identifiant.

Les conditions suivantes sont vérifiées :

- `grantResults.isNotEmpty()` : S'assure que le tableau des résultats n'est pas vide.
- `grantResults[0] == granted` : Vérifie si la première permission demandée (typiquement `ACCESS_FINE_LOCATION` pour la détection BLE) a été accordée.

Si la permission a été accordée, la méthode `startBleScan` est appelée pour démarrer le scan. Si non, une gestion d'erreur peut être implémentée (pas dans notre cas).

```kotlin
override fun onRequestPermissionsResult(
    requestCode: Int,
    permissions: Array<out String>,
    grantResults: IntArray
) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    when (requestCode) {
        REQUEST_PERMISSIONS_CODE -> {
            if (grantResults.isNotEmpty() && grantResults[0] == granted) {
                // Permission granted, start the scan
                startBleScan()
            } else {
                // Permission denied, handle the failure
            }
        }
    }
}
```



### Connexion à un server GATT ([doc](https://developer.android.com/develop/connectivity/bluetooth/ble/connect-gatt-server))

Cette étape n'a pas été implémentée dans notre application par manque de temps, mais aussi, car nous ne disposions pas de devices BLE pour tester l'implémentation. La marche à suivre suivante est un résumé de la documentation officielle Android. Pour obtenir plus de détails concernant l'implémentation, veuillez vous référer à la documentation officielle.

1. Pour se connecter à un serveur GATT sur un appareil BLE, il faut utiliser la méthode `connectGatt()` avec trois paramètres : un objet `Context`, un booléen `autoConnect` indiquant si la connexion doit être automatique, et une référence à un `BluetoothGattCallback`. Cette méthode retourne une instance de `BluetoothGatt`, utilisée pour les opérations du client GATT.

```kotlin
var bluetoothGatt: BluetoothGatt? = device.connectGatt(this, false, gattCallback)
```

2. Ensuite, il faut créer un `Service` (par exemple `BluetoothLeService`) pour interagir avec le device BLE via l'API BLE. Il faut utiliser `bindService()` pour lier l'activité au service. Le `Service` a besoin d'une implémentation de `Binder` pour fournir un accès au service.

   ```kotlin
   class BluetoothLeService : Service() {
       private val binder = LocalBinder()
       override fun onBind(intent: Intent): IBinder? {
           return binder
       }
       inner class LocalBinder : Binder() {
           fun getService() : BluetoothLeService {
               return this@BluetoothLeService
           }
       }
   }
   ```

   

   L'activité peut démarrer le service à l'aide de `bindService()`, en transmettant un `Intent` pour démarrer le service, une implémentation `ServiceConnection` pour écouter les événements de connexion et de déconnexion, et un flag pour spécifier des options de connexion supplémentaires.

   ```kotlin
   class MainActivity : AppCompatActivity() {
   
       private var bluetoothService : BluetoothLeService? = null
   
       // Code to manage Service lifecycle.
       private val serviceConnection: ServiceConnection = object : ServiceConnection {
           override fun onServiceConnected(
               componentName: ComponentName,
               service: IBinder
           ) {
               bluetoothService = (service as LocalBinder).getService()
               bluetoothService?.let { bluetooth ->
                   // call functions on service to check connection and connect to devices
               }
           }
           override fun onServiceDisconnected(componentName: ComponentName) {
               bluetoothService = null
           }
       }
   
       override fun onCreate(savedInstanceState: Bundle?) {
           super.onCreate(savedInstanceState)
           val gattServiceIntent = Intent(this, BluetoothLeService::class.java)
           bindService(gattServiceIntent, serviceConnection, Context.BIND_AUTO_CREATE)
       }
   }
   
   ```

   

3. Il faut ensuite s'assurer que le `BluetoothAdapter` est disponible sur l'appareil, car il est nécessaire pour interagir avec les appareils BLE. La méthode `initialize()` du service vérifie la disponibilité de l'adaptateur et retourne un booléen indiquant le succès.

   ```kotlin
   private const val TAG = "BluetoothLeService"
   
   class BluetoothLeService : Service() {
   
       private var bluetoothAdapter: BluetoothAdapter? = null
   
       fun initialize(): Boolean {
           bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
           if (bluetoothAdapter == null) {
               Log.e(TAG, "Unable to obtain a BluetoothAdapter.")
               return false
           }
           return true
       }
       ...
   }
   ```


L'activité appelle cette fonction dans son implémentation `ServiceConnection`. Le traitement d'une valeur de retour false de la fonction initialize() dépend de notre application. On peut afficher un message d'erreur indiquant à l'utilisateur que l'appareil actuel ne prend pas en charge l'opération Bluetooth ou désactiver toutes les fonctions qui nécessitent le Bluetooth pour fonctionner. Dans l'exemple suivant, la fonction finish() est appelée sur l'activité pour renvoyer l'utilisateur à l'écran précédent.

   ```kotlin
   class MainActivity : AppCompatActivity() {
   
       // Code to manage Service lifecycle.
       private val serviceConnection: ServiceConnection = object : ServiceConnection {
           override fun onServiceConnected(
               componentName: ComponentName,
               service: IBinder
           ) {
               bluetoothService = (service as LocalBinder).getService()
               bluetoothService?.let { bluetooth ->
                   if (!bluetooth.initialize()) {
                       Log.e(TAG, "Unable to initialize Bluetooth")
                       finish()
                   }
                   // perform device connection
               }
           }
           override fun onServiceDisconnected(componentName: ComponentName) {
               bluetoothService = null
           }
       }
       ...
   }
   
   ```

4. Une fois que le service Bluetooth est initialisé, il peut se connecter à l'appareil. L'activité doit envoyer l'adresse de l'appareil au service pour qu'il puisse établir la connexion. Le service appellera d'abord `getRemoteDevice()` sur le `BluetoothAdapter` pour accéder au périphérique. Si l'adaptateur ne parvient pas à trouver un périphérique avec cette adresse, `getRemoteDevice()` lève une exception de type `IllegalArgumentException`.

   ```kotlin
   
   fun connect(address: String): Boolean {
       bluetoothAdapter?.let { adapter ->
           try {
               val device = adapter.getRemoteDevice(address)
           } catch (exception: IllegalArgumentException) {
               Log.w(TAG, "Device not found with provided address.")
               return false
           }
       // connect to the GATT server on the device
       } ?: run {
           Log.w(TAG, "BluetoothAdapter not initialized")
           return false
       }
   }
   ```



4. Pour recevoir des notifications sur l'état de la connexion, la découverte de service et d'autres événements GATT, il faut déclarer le  `BluetoothGattCallback`. Utiliser `onConnectionStateChange()` pour gérer les changements d'état de connexion.
5. Ensuite, il faut utiliser `connectGatt()` pour se connecter au service GATT de l'appareil. Le service utilise `BluetoothGattCallback` pour la connexion.
6. Le service doit informer l'activité des changements d'état. Pour ça, on utilise des broadcasts pour envoyer les informations du service à l'activité. L'activité doit donc implémenter un `BroadcastReceiver` pour recevoir et traiter les mises à jour de l'état de la connexion envoyées par le service.
7. Finalement,  il faut fermer la connexion GATT lorsque celle-ci n'est plus nécessaire, en appelant `close()` sur l'objet `BluetoothGatt` pour éviter de consommer inutilement la batterie de l'appareil.



## Conclusion (Tim)





## Bibliographie:

* [Documentation Android](https://developer.android.com/develop/connectivity/bluetooth/ble/ble-overview)

* Nous avons utilisé Chat GPT pour améliorer la rédaction du rapport, mais pas pour écrire le fond
