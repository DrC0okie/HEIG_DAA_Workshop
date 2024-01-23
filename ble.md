## Permissions Bluetooth

### Déclaration des permissions

Les applications utilisant le Bluetooth doivent déclarer des permissions spécifiques dans le manifest de l'application, avec des exigences variant selon la version cible du SDK Android.

#### Pour Android 12 ou supérieur

- Utiliser la permission `BLUETOOTH_SCAN` pour rechercher des appareils Bluetooth, comme les périphériques BLE.
- Utiliser la permission `BLUETOOTH_ADVERTISE` si l'application rend l'appareil courant découvrable par d'autres appareils Bluetooth.
- Utiliser la permission `BLUETOOTH_CONNECT` pour communiquer avec des appareils Bluetooth déjà appairés.
- Déclarer `ACCESS_FINE_LOCATION` si l'application utilise les résultats de la recherche Bluetooth pour déterminer la position physique. Sinon, on peut affirmer fortement que l'application ne dérive pas la position physique à partir des résultats de la recherche Bluetooth.
- Les permissions `BLUETOOTH_ADVERTISE`, `BLUETOOTH_CONNECT` et `BLUETOOTH_SCAN` sont des permissions d'exécution, nécessitant l'approbation de l'utilisateur.

#### Pour Android 11 ou inférieur

- Utilisez la permission `BLUETOOTH` pour toute communication via Bluetooth classique ou BLE.
- Déclarer `ACCESS_FINE_LOCATION` car une recherche Bluetooth pourrait potentiellement être utilisée pour recueillir des informations sur la localisation de l'utilisateur.

### Découverte des appareils Bluetooth locaux

- Déclarer la permission `BLUETOOTH_ADMIN` pour permettre à l'application d'initier la découverte d'appareils ou de manipuler les paramètres Bluetooth.

### Spécification de l'utilisation des fonctionnalités Bluetooth

- Ajouter des indicateurs dans le manifest indiquant si le Bluetooth est une composante essentielle de l'application, en utilisant l'élément `<uses-feature>`. Par exemple: `android.hardware.bluetooth` pour le Bluetooth classique et `android.hardware.bluetooth_le` pour le BLE.

### Vérification de la disponibilité des fonctionnalités au runtime

- Pour rendre l'application disponible sur des appareils ne supportant pas le Bluetooth classique ou le BLE, incure l'élément `<uses-feature>` dans le manifest avec `required="false"`. Ensuite, vérifier la disponibilité des fonctionnalités au runtime en utilisant `PackageManager.hasSystemFeature()`.

### Sommaire:

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

