package xyz.tusion.baltseahack_androidapp.presentation.map;

import androidx.fragment.app.Fragment;

public class MapViewFragmentS extends Fragment
{
    /*
    boolean firstClick = true;
    LoadingView dialog;
    List<SharePoint> sharePoints;
    MapView mMapView;
    boolean fromQRCode = false;
    private GoogleMap googleMap;
    FloatingActionButton myLocationBtn;
    CheckBox glassFilter, paperFilter, plasticFilter, metalFilter;
    ConstraintLayout glassTrashBtn, paperTrashBtn, plasticTrashBtn, metalTrashBtn;
    String[] permissions = {Manifest.permission.ACCESS_COARSE_LOCATION};
    ArrayList<Marker> paperMarkers = new ArrayList<>();
    ArrayList<Marker> glassMarkers = new ArrayList<>();
    ArrayList<Marker> plasticMarkers = new ArrayList<>();
    ArrayList<Marker> metalMarkers = new ArrayList<>();
    RelativeLayout mainLay;
    private ImageView locationButton;

    private LinearLayout bottomSheet;
    private TextView pointName, pointStreet, pointTrashTypes,
            pointOperationMode, trashPointState, directionToTrashPoint;
    private MarkerOptions QRmarkerOption;
    private TextView routeNavigator;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        dialog = LoadingDialog.view(getFragmentManager());
        View rootView = inflater.inflate(R.layout.container_view, container, false);
        mMapView = (MapView) rootView.findViewById(R.id.mapViewNew);
        mMapView.onCreate(savedInstanceState);


        mMapView.onResume(); // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        initMap();
        return rootView;
    }


    @SuppressLint("ResourceType")
    private void initMap() {
        Log.e("Called map async", "initMap: ");
        mMapView.getMapAsync(mMap -> {
            googleMap = mMap;
//            LatLng sydney = new LatLng(-34, 151);
//            googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker Title").snippet("Marker Description"));
//
//            // For zooming automatically to the location of the marker
//            CameraPosition cameraPosition = new CameraPosition.Builder().target(sydney).zoom(12).build();
//            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            locationButton = ((View) mMapView.findViewById(1).getParent()).findViewById(2);
            // Change the visibility of my location button
//            locationButton.setVisibility(View.GONE);
            googleMap.getUiSettings().setCompassEnabled(false);
            if (!fromQRCode) {
                getTrashCollectionPoints();}
            googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {
                    MapViewFragmentS.this.onMarkerClick(marker);
                   return true;
                }
            });
//            ensurePermissions(requireActivity());
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        try {
            if (getArguments().getString("marker") != null) {
                getTrashPoint(getArguments().getString("id"));
                fromQRCode = true;
            }
        } catch (Exception e) {

        }
        super.onViewCreated(view, savedInstanceState);
        bottomSheet = view.findViewById(R.id.bottom_sheet_reference);
        myLocationBtn = view.findViewById(R.id.myLocationButton);
        mainLay = view.findViewById(R.id.main_lay_reference);
        glassFilter = mainLay.findViewById(R.id.glass_filter_btn);
        glassTrashBtn = mainLay.findViewById(R.id.glass_trash_btn);
        paperFilter = mainLay.findViewById(R.id.paper_filter_btn);
        paperTrashBtn = mainLay.findViewById(R.id.paper_trash_btn);
        plasticFilter = mainLay.findViewById(R.id.plastic_filter_btn);
        plasticTrashBtn = mainLay.findViewById(R.id.plastic_trash_btn);
        metalFilter = mainLay.findViewById(R.id.metal_filter_btn);
        metalTrashBtn = mainLay.findViewById(R.id.metal_trash_btn);

        pointName = bottomSheet.findViewById(R.id.pointName_bottom_sheet);
        pointStreet = bottomSheet.findViewById(R.id.pointStreet_bottom_sheet);
        pointTrashTypes = bottomSheet.findViewById(R.id.pointTrashTypes_bottom_sheet);
        pointOperationMode = bottomSheet.findViewById(R.id.pointOperationMode_bottom_sheet);
        trashPointState = bottomSheet.findViewById(R.id.trashPointState_bottom_sheet);
        directionToTrashPoint = bottomSheet.findViewById(R.id.directionToTrashPointBtn_bottom_sheet);

        glassTrashBtn.setOnClickListener(view1 -> glassFilter.performClick());
        paperTrashBtn.setOnClickListener(view1 -> paperFilter.performClick());
        plasticTrashBtn.setOnClickListener(view1 -> plasticFilter.performClick());
        metalTrashBtn.setOnClickListener(view1 -> metalFilter.performClick());

        myLocationBtn.setOnClickListener(view12 -> {
                    CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(55.798551, 49.106324)).zoom(12).build();
                    googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        });




        glassFilter.setOnCheckedChangeListener((glassFilter, checked) -> {
            if (checked) {
                if (firstClick) {
                    plasticMarkers.forEach(marker -> marker.setVisible(false));
                    metalMarkers.forEach(marker -> marker.setVisible(false));
                    paperMarkers.forEach(marker -> marker.setVisible(false));
                    firstClick = false;
                }
                glassMarkers.forEach(marker -> marker.setVisible(true));
            } else {
                glassMarkers.forEach(marker -> marker.setVisible(false));
            }
        });
        paperFilter.setOnCheckedChangeListener((paperFilter, checked) -> {
            if (checked) {
                if (firstClick) {
                    plasticMarkers.forEach(marker -> marker.setVisible(false));
                    metalMarkers.forEach(marker -> marker.setVisible(false));
                    glassMarkers.forEach(marker -> marker.setVisible(false));
                    firstClick = false;
                }
                paperMarkers.forEach(marker -> marker.setVisible(true));
            } else {
                paperMarkers.forEach(marker -> marker.setVisible(false));
            }
        });
        plasticFilter.setOnCheckedChangeListener((plasticFilter, checked) -> {
            if (checked) {
                if (firstClick) {
                    glassMarkers.forEach(marker -> marker.setVisible(false));
                    metalMarkers.forEach(marker -> marker.setVisible(false));
                    paperMarkers.forEach(marker -> marker.setVisible(false));
                    firstClick = false;
                }
                plasticMarkers.forEach(marker -> marker.setVisible(true));
            } else {
                plasticMarkers.forEach(marker -> marker.setVisible(false));
            }
        });
        metalFilter.setOnCheckedChangeListener((metalFilter, checked) -> {
            if (checked) {
                if (firstClick) {
                    plasticMarkers.forEach(marker -> marker.setVisible(false));
                    glassMarkers.forEach(marker -> marker.setVisible(false));
                    paperMarkers.forEach(marker -> marker.setVisible(false));
                    firstClick = false;
                }
                metalMarkers.forEach(marker -> marker.setVisible(true));
            } else {
                metalMarkers.forEach(marker -> marker.setVisible(false));
            }
        });
    }

    private void getTrashPoint(String id) {
        RepositoryProvider
                .getJsonRepository()
                .getSeparateCollectionPointById(id)
                .subscribe(
                        this::onPointGot,
                        this::onError
                );
    }

    private void onPointGot(SharePoint sharePoint) {
        BitmapDescriptor descriptor = BitmapDescriptorFactory.fromResource(R.drawable.point_icon);
        QRmarkerOption = new MarkerOptions()
                .position(new LatLng(sharePoint.getLatitude(), sharePoint.getLongitude()))
                .title(sharePoint.getInfo())
                .icon(descriptor);
        pointName.setText(sharePoint.getInfo());
        pointStreet.setText(sharePoint.getAddress());
        StringBuilder stringBuilder = new StringBuilder();
        sharePoint.getTrashTypes().forEach((trashType) -> stringBuilder.append(trashType.getName()).append(", "));
        pointOperationMode.setText(sharePoint.getOpenHours());
        if (!sharePoint.getFull()) {
            trashPointState.setText("Свободен");
            trashPointState.setBackgroundColor(requireContext().getColor(R.color.dark_green));
        }
        googleMap.addMarker(QRmarkerOption);
        CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(55.798551, 49.106324)).zoom(8).build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        directionToTrashPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Создаем интент для построения маршрута
                Intent intent = new Intent("ru.yandex.yandexnavi.action.BUILD_ROUTE_ON_MAP");
                intent.setPackage("ru.yandex.yandexnavi");

                PackageManager pm = requireActivity().getPackageManager();
                List<ResolveInfo> infos = pm.queryIntentActivities(intent, 0);

                // Проверяем, установлен ли Яндекс.Навигатор
                if (infos == null || infos.size() == 0) {
                    // Если нет - будем открывать страничку Навигатора в Google Play
                    intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("market://details?id=ru.yandex.yandexnavi"));
                } else {
                    intent.putExtra("lat_from", 55.798551);
                    intent.putExtra("lon_from", 49.106324);
                    intent.putExtra("lat_to", sharePoint.getLatitude());
                    intent.putExtra("lon_to", sharePoint.getLongitude());
                }

                // Запускаем нужную Activity
                startActivity(intent);
            }
        });
    }

    private void getTrashCollectionPoints() {
        RepositoryProvider
                .getJsonRepository()
                .getSeparateCollectionPoints()
                .doOnSubscribe(d -> dialog.showLoadingIndicator())
                .doAfterTerminate(dialog::hideLoadingIndicator)
                .subscribe(this::onSuccess, this::onError);
    }

    private void onError(Throwable throwable) {
        throwable.fillInStackTrace();
    }
    private void onSuccess(List<SharePoint> sharePoints) {
        this.sharePoints = sharePoints;
        BitmapDescriptor descriptor = BitmapDescriptorFactory.fromResource(R.drawable.point_icon);
        Log.e("SUCCESS", "onSuccess:");
        String title;
        Marker marker;
        for (SharePoint sharePoint : sharePoints) {
            if (sharePoint.getInfo() != null) title = sharePoint.getInfo();
            else title = "unknown";
            LatLng sharePointLatLng = new LatLng(sharePoint.getLatitude(), sharePoint.getLongitude());
            MarkerOptions sharePointMarkerOptions = new MarkerOptions()
                    .position(sharePointLatLng)
                    .title(title)
                    .icon(descriptor);
            Log.e("S u c c ", "onSuccess:");
            if (sharePoint.getTrashTypes() != null) {
            for (TrashType trashType : sharePoint.getTrashTypes()) {
                if (trashType.getName() != null) {
                    System.out.println("TRASH TYPE: " + trashType.getName());
                switch (trashType.getName()) {
                    case "Бумага":
                        marker = googleMap.addMarker(sharePointMarkerOptions);
                        marker.setVisible(true);
                        paperMarkers.add(marker);
                        break;
                    case "Металл":
                        marker = googleMap.addMarker(sharePointMarkerOptions);
                        marker.setVisible(true);
                        metalMarkers.add(marker);
                        break;
                    case "Стекло":
                        marker = googleMap.addMarker(sharePointMarkerOptions);
                        marker.setVisible(true);
                        glassMarkers.add(marker);
                        break;
                    case "Пластик":
                        marker = googleMap.addMarker(sharePointMarkerOptions);
                        marker.setVisible(true);
                        plasticMarkers.add(marker);
                        break;
                    default:
                        Log.e("TimeOption", "onDefault: " );
                }
            }}
        }}
        Log.e("S u c c e s s", "onSuccess:");
//        ensurePermissions(requireActivity());
        CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(55.798551, 49.106324)).zoom(8).build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//            googleMap.setMyLocationEnabled(true);
//                googleMap.getUiSettings().setMyLocationButtonEnabled(true);
//                locationButton.setVisibility(View.GONE);
//        } else {
//            Toast.makeText(requireContext(), "Permission denied to check your location", Toast.LENGTH_SHORT).show();
//        }
//    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

//    public void ensurePermissions(Activity activity) {
//        if (ContextCompat.checkSelfPermission(
//                activity, Manifest.permission.ACCESS_COARSE_LOCATION
//        ) != PackageManager.PERMISSION_GRANTED
//        ) {
//            ActivityCompat.requestPermissions(
//                    activity, permissions, 0
//            );
//        } else {
//            googleMap.setMyLocationEnabled(true);
//            googleMap.getUiSettings().setMyLocationButtonEnabled(true);
//            locationButton.setVisibility(View.GONE);
//        }
//    }

    public void onMarkerClick(Marker marker) {
        Log.e("CLICK MARKER MAP", "onMarkerClick: ");
        if (!fromQRCode) {
            for (SharePoint sharePoint : sharePoints) {
                if (sharePoint.getInfo().equals(marker.getTitle())) {
                    pointName.setText(sharePoint.getInfo());
                    pointStreet.setText(sharePoint.getAddress());
                    StringBuilder stringBuilder = new StringBuilder();
                    sharePoint.getTrashTypes().forEach((trashType) -> stringBuilder.append(trashType.getName()).append(", "));
                    pointOperationMode.setText(sharePoint.getOpenHours());
                    if (!sharePoint.getFull()) {
                        trashPointState.setText("Свободен");
                        trashPointState.setBackgroundColor(requireContext().getColor(R.color.dark_green));
                    }
                    // TODO: 9/29/2019 маршрут
//                directionToTrashPoint

                }
            }
        }
        bottomSheet.setVisibility(View.VISIBLE);
//        myLocationBtn.setVisibility(View.INVISIBLE);
    }

     */
}