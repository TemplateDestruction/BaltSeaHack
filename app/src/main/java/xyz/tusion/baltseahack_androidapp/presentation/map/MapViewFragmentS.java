package xyz.tusion.baltseahack_androidapp.presentation.map;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import xyz.tusion.baltseahack_androidapp.R;
import xyz.tusion.baltseahack_androidapp.domain.model.Club;
import xyz.tusion.baltseahack_androidapp.domain.repository.RepositoryProvider;
import xyz.tusion.baltseahack_androidapp.presentation.base.BaseFragment;
import xyz.tusion.baltseahack_androidapp.presentation.standard.LoadingDialog;
import xyz.tusion.baltseahack_androidapp.presentation.standard.LoadingView;

public class MapViewFragmentS extends BaseFragment {


    boolean firstClick = true;
    LoadingView dialog;
    ArrayList<Club> clubsList;
    MapView mMapView;
    boolean fromSinglePoint = false;
    private GoogleMap googleMap;
    FloatingActionButton myLocationBtn;
    FloatingActionButton searchButton;
    //    CheckBox glassFilter, paperFilter, plasticFilter, metalFilter;
//    ConstraintLayout glassTrashBtn, paperTrashBtn, plasticTrashBtn, metalTrashBtn;
    String[] permissions = {Manifest.permission.ACCESS_COARSE_LOCATION};
    ArrayList<Marker> paperMarkers = new ArrayList<>();
    ArrayList<Marker> glassMarkers = new ArrayList<>();
    ArrayList<Marker> plasticMarkers = new ArrayList<>();
    ArrayList<Marker> metalMarkers = new ArrayList<>();
    RelativeLayout mainLay;
    private ImageView locationButton;

    private LinearLayout bottomSheet;
    private TextView pointName, pointStreet, pointTrashTypes,
            pointOperationMode, directionToTrashPoint;
    private MarkerOptions QRmarkerOption;
    private TextView routeNavigator;
    private Club singleClub;

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
            if (!fromSinglePoint) {
                getTrashCollectionPoints();
            } else {
                onPointGot(singleClub);
            }
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
            if (getArguments().getString("dich") != null) {
                Log.e("MapViewFrag", "onViewCreated: dich");
            }
            if (getArguments().getParcelable("myArg") != null) {
                fromSinglePoint = true;
                singleClub = getArguments().getParcelable("myArg");
            }

        } catch (Exception e) {

        }
        super.onViewCreated(view, savedInstanceState);
        bottomSheet = view.findViewById(R.id.bottom_sheet_reference);
        myLocationBtn = view.findViewById(R.id.myLocationButton);
        searchButton = view.findViewById(R.id.searchButton);
        mainLay = view.findViewById(R.id.main_lay_reference);
//        glassFilter = mainLay.findViewById(R.id.glass_filter_btn);
//        glassTrashBtn = mainLay.findViewById(R.id.glass_trash_btn);
//        paperFilter = mainLay.findViewById(R.id.paper_filter_btn);
//        paperTrashBtn = mainLay.findViewById(R.id.paper_trash_btn);
//        plasticFilter = mainLay.findViewById(R.id.plastic_filter_btn);
//        plasticTrashBtn = mainLay.findViewById(R.id.plastic_trash_btn);
//        metalFilter = mainLay.findViewById(R.id.metal_filter_btn);
//        metalTrashBtn = mainLay.findViewById(R.id.metal_trash_btn);

        pointName = bottomSheet.findViewById(R.id.pointName_bottom_sheet);
        pointStreet = bottomSheet.findViewById(R.id.pointStreet_bottom_sheet);
        pointTrashTypes = bottomSheet.findViewById(R.id.pointTrashTypes_bottom_sheet);
        pointOperationMode = bottomSheet.findViewById(R.id.pointOperationMode_bottom_sheet);

        directionToTrashPoint = bottomSheet.findViewById(R.id.directionToTrashPointBtn_bottom_sheet);
//
//        glassTrashBtn.setOnClickListener(view1 -> glassFilter.performClick());
//        paperTrashBtn.setOnClickListener(view1 -> paperFilter.performClick());
//        plasticTrashBtn.setOnClickListener(view1 -> plasticFilter.performClick());
//        metalTrashBtn.setOnClickListener(view1 -> metalFilter.performClick());

        myLocationBtn.setOnClickListener(view12 -> {
            CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(59.7620964, 30.3551396)).zoom(15).build();
            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        });
        searchButton.setOnClickListener(view1 -> {
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("clubs", clubsList);

            Navigation.findNavController(view1).navigate(R.id.action_mapFragment_to_searchFragment, bundle);
        });


//        glassFilter.setOnCheckedChangeListener((glassFilter, checked) -> {
//            if (checked) {
//                if (firstClick) {
//                    plasticMarkers.forEach(marker -> marker.setVisible(false));
//                    metalMarkers.forEach(marker -> marker.setVisible(false));
//                    paperMarkers.forEach(marker -> marker.setVisible(false));
//                    firstClick = false;
//                }
//                glassMarkers.forEach(marker -> marker.setVisible(true));
//            } else {
//                glassMarkers.forEach(marker -> marker.setVisible(false));
//            }
//        });
//        paperFilter.setOnCheckedChangeListener((paperFilter, checked) -> {
//            if (checked) {
//                if (firstClick) {
//                    plasticMarkers.forEach(marker -> marker.setVisible(false));
//                    metalMarkers.forEach(marker -> marker.setVisible(false));
//                    glassMarkers.forEach(marker -> marker.setVisible(false));
//                    firstClick = false;
//                }
//                paperMarkers.forEach(marker -> marker.setVisible(true));
//            } else {
//                paperMarkers.forEach(marker -> marker.setVisible(false));
//            }
//        });
//        plasticFilter.setOnCheckedChangeListener((plasticFilter, checked) -> {
//            if (checked) {
//                if (firstClick) {
//                    glassMarkers.forEach(marker -> marker.setVisible(false));
//                    metalMarkers.forEach(marker -> marker.setVisible(false));
//                    paperMarkers.forEach(marker -> marker.setVisible(false));
//                    firstClick = false;
//                }
//                plasticMarkers.forEach(marker -> marker.setVisible(true));
//            } else {
//                plasticMarkers.forEach(marker -> marker.setVisible(false));
//            }
//        });
//        metalFilter.setOnCheckedChangeListener((metalFilter, checked) -> {
//            if (checked) {
//                if (firstClick) {
//                    plasticMarkers.forEach(marker -> marker.setVisible(false));
//                    glassMarkers.forEach(marker -> marker.setVisible(false));
//                    paperMarkers.forEach(marker -> marker.setVisible(false));
//                    firstClick = false;
//                }
//                metalMarkers.forEach(marker -> marker.setVisible(true));
//            } else {
//                metalMarkers.forEach(marker -> marker.setVisible(false));
//            }
//        });
    }

    private void getClub(String id) {
        RepositoryProvider
                .getJsonRepository()
                .getSingleClubById(id)
                .subscribe(
                        this::onPointGot,
                        this::onError
                );
    }

    private void onPointGot(Club club) {
        BitmapDescriptor descriptor = BitmapDescriptorFactory.fromResource(R.drawable.icons48);
        QRmarkerOption = new MarkerOptions()
                .position(new LatLng(club.getLongtitude(), club.getLatitude()))
                .title(club.getName())
                .icon(descriptor);
        pointName.setText(club.getName());
        pointStreet.setText(club.getAdress());
        StringBuilder stringBuilder = new StringBuilder();
        club.getSections().forEach((trashType) -> stringBuilder.append(trashType.getName()).append(", "));
        String str = stringBuilder.toString();
        pointTrashTypes.setText(str.replace(str.charAt(str.length() - 2), '\u0000'));
        googleMap.addMarker(QRmarkerOption);
        CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(club.getLongtitude(), club.getLatitude())).zoom(8).build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        directionToTrashPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri gmmIntentUri = Uri.parse("google.navigation:q=" + club.getLongtitude() + "," + club.getLatitude());
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });
    }

    private void getTrashCollectionPoints() {
        RepositoryProvider
                .getJsonRepository()
                .getClubs()
                .doOnSubscribe(d -> dialog.showLoadingIndicator())
                .doAfterTerminate(dialog::hideLoadingIndicator)
                .subscribe(this::onSuccess, this::onError);
    }

    private void onError(Throwable throwable) {
        throwable.fillInStackTrace();
    }

    private void onSuccess(ArrayList<Club> clubs) {
        this.clubsList = clubs;
        BitmapDescriptor descriptor = BitmapDescriptorFactory.fromResource(R.drawable.icons48);
        Log.e("SUCCESS", "onSuccess:");
        String title;
        Marker marker;
        for (Club club : clubs) {
            if (club.getName() != null) title = club.getName();
            else title = "unknown";
            LatLng sharePointLatLng = new LatLng(club.getLongtitude(), club.getLatitude());
            MarkerOptions sharePointMarkerOptions = new MarkerOptions()
                    .position(sharePointLatLng)
                    .title(title)
                    .icon(descriptor);
            Log.e("S u c c ", "onSuccess:");
            marker = googleMap.addMarker(sharePointMarkerOptions);
            marker.setVisible(true);
            paperMarkers.add(marker);
        }
        Log.e("S u c c e s s", "onSuccess:");
//        ensurePermissions(requireActivity());
        CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(59.7620964, 30.3551396)).zoom(8).build();
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
        if (!fromSinglePoint) {
            for (Club club : clubsList) {
                if (club.getName().equals(marker.getTitle())) {
                    pointName.setText(club.getName());
                    pointStreet.setText(club.getAdress());
                    StringBuilder stringBuilder = new StringBuilder();
                    club.getSections().forEach((trashType) -> stringBuilder.append(trashType.getName()).append(", "));
                    String str = stringBuilder.toString();
                    pointTrashTypes.setText(str.replace(str.charAt(str.length() - 2), '\u0000'));
                    // TODO: 9/29/2019 маршрут
//                directionToTrashPoint
                    directionToTrashPoint.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            // Создаем интент для построения маршрута
//                            Intent intent = new Intent("ru.yandex.yandexnavi.action.BUILD_ROUTE_ON_MAP");
//                            intent.setPackage("ru.yandex.yandexnavi");
//
//                            PackageManager pm = requireActivity().getPackageManager();
//                            List<ResolveInfo> infos = pm.queryIntentActivities(intent, 0);
//
//                            // Проверяем, установлен ли Яндекс.Навигатор
//                            if (infos == null || infos.size() == 0) {
//                                // Если нет - будем открывать страничку Навигатора в Google Play
//                                intent = new Intent(Intent.ACTION_VIEW);
//                                intent.setData(Uri.parse("market://details?id=ru.yandex.yandexnavi"));
//                            } else {
//                                intent.putExtra("lat_from", 59.7620964);
//                                intent.putExtra("lon_from", 30.3551396);
//                                intent.putExtra("lat_to", club.getLongtitude());
//                                intent.putExtra("lon_to", club.getLatitude());
//                            }
//
//                            // Запускаем нужную Activity
//                            startActivity(intent);
                            // Create a Uri from an intent string. Use the result to create an Intent.
                            Uri gmmIntentUri = Uri.parse("google.navigation:q=" + club.getLongtitude() + "," + club.getLatitude());
                            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                            mapIntent.setPackage("com.google.android.apps.maps");
                            startActivity(mapIntent);
                        }
                    });
                }
            }
        }
        bottomSheet.setVisibility(View.VISIBLE);
//        myLocationBtn.setVisibility(View.INVISIBLE);
    }


}