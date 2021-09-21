"use strict";
// Class definition

//var KTAppsUsersListDatatable = function () {
//    // Private functions
//
//    // basic demo
//    var _demo = function () {
//        var datatable = $('#kt_datatable').KTDatatable({
//            // datasource definition
//            data: {
//                type: 'remote',
//                source: {
//                    read: {
//                        url: HOST_URL + '/bookings/all',
//                    },
//                },
//                pageSize: 10, // display 20 records per page
//                serverPaging: true,
//                serverFiltering: true,
//                serverSorting: true,
//            },
//
//            // layout definition
//            layout: {
//                scroll: false, // enable/disable datatable scroll both horizontal and vertical when needed.
//                footer: false, // display/hide footer
//            },
//
//            // column sorting
//            sortable: true,
//
//            pagination: true,
//
//            search: {
//                input: $('#kt_subheader_search_form'),
//                delay: 400,
//                key: 'generalSearch'
//            },
//
//            // columns definition
//            columns: [
//                {
//                    field: 'fecha',
//                    title: 'Fecha',
//                    width: 75,
//                    template: function(row) {
//                        let date = moment(row.fecha.toLocaleString());
//                        return date.format("DD-MM-YYYY")
//                    }
//                },
//                {
//                    field: 'hora',
//                    title: 'Hora',
//                    width: 75,
//                    template: function(row) {
//                        var time = row.hora;
//                        var day = moment().zone('GMT');
//                        var splitTime = time.split(/:/)
//                        day.hours(parseInt(splitTime[0])).minutes(parseInt(splitTime[1])).seconds(0).milliseconds(0);
//                        return day.format("HH:mm");
//                    }
//                },
//                {
//                    field: 'cancha.nombre',
//                    title: 'Cancha',
//                    width: 100,
//                },
//                {
//                    field: 'cancha.descripcion',
//                    title: 'Tipo',
//                    width: 100,
//                },
//                {
//                    field: 'usuario.nombre_completo',
//                    title: 'Usuario',
//                    width: 150,
//                },
//                {
//                    field: 'pagada',
//                    title: 'Estado',
//                    width: 150,
//                    template: function (row) {
//                        var status = {
//                            false: {'title': 'Pendiente', 'class': ' label-light-warning'},
//                            true: {'title': 'Pagada', 'class': ' label-light-success'},
//                        };
//                        return '<span class="label label-lg font-weight-bold ' + status[row.pagada].class + ' label-inline">' + status[row.pagada].title + '</span>';
//                    },
//                },
//                {
//                    field: 'creado',
//                    title: 'Creado',
//                    width: 90,
//                    template: function(row) {
//                        let date = moment(row.creado.toLocaleString());
//                        return date.format("DD-MM-YYYY HH:mm:ss")
//                    }
//                },
//                {
//                    field: 'modificado',
//                    title: 'Modificado',
//                    width: 90,
//                    template: function(row) {
//                        let date = moment(row.modificado.toLocaleString());
//                        return date.format("DD-MM-YYYY HH:mm:ss")
//                    }
//                },
//                {
//                    field: 'Actions',
//                    title: 'Actions',
//                    sortable: false,
//                    width: 130,
//                    overflow: 'visible',
//                    autoHide: false,
//                    template: function (row) {
//                        
//                        var btns = '';
//                        
//                        if ( $("#rol").val() === "Administrador" ) {
//                            btns = 
//                            '\
//	                        <a href="' + HOST_URL + '/bookings/edit/' + row.id + '" class="btn btn-sm btn-default btn-text-primary btn-hover-primary btn-icon mr-2" title="Edit details">\
//	                            <span class="svg-icon svg-icon-md">\
//                                        <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" width="24px" height="24px" viewBox="0 0 24 24" version="1.1">\
//                                            <g stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">\
//                                                <rect x="0" y="0" width="24" height="24"/>\
//                                                <path d="M12.2674799,18.2323597 L12.0084872,5.45852451 C12.0004303,5.06114792 12.1504154,4.6768183 12.4255037,4.38993949 L15.0030167,1.70195304 L17.5910752,4.40093695 C17.8599071,4.6812911 18.0095067,5.05499603 18.0083938,5.44341307 L17.9718262,18.2062508 C17.9694575,19.0329966 17.2985816,19.701953 16.4718324,19.701953 L13.7671717,19.701953 C12.9505952,19.701953 12.2840328,19.0487684 12.2674799,18.2323597 Z" fill="#000000" fill-rule="nonzero" transform="translate(14.701953, 10.701953) rotate(-135.000000) translate(-14.701953, -10.701953) "/>\
//                                                <path d="M12.9,2 C13.4522847,2 13.9,2.44771525 13.9,3 C13.9,3.55228475 13.4522847,4 12.9,4 L6,4 C4.8954305,4 4,4.8954305 4,6 L4,18 C4,19.1045695 4.8954305,20 6,20 L18,20 C19.1045695,20 20,19.1045695 20,18 L20,13 C20,12.4477153 20.4477153,12 21,12 C21.5522847,12 22,12.4477153 22,13 L22,18 C22,20.209139 20.209139,22 18,22 L6,22 C3.790861,22 2,20.209139 2,18 L2,6 C2,3.790861 3.790861,2 6,2 L12.9,2 Z" fill="#000000" fill-rule="nonzero" opacity="0.3"/>\
//                                            </g>\
//                                        </svg>\
//	                            </span>\
//	                        </a>\
//	                        <a href="javascript:;" onclick="deleteUser(' + '\'/bookings\',' + row.id + ')" class="btn btn-sm btn-default btn-text-primary btn-hover-primary btn-icon mr-2" title="Delete">\
//                                    <span class="svg-icon svg-icon-md">\
//                                        <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" width="24px" height="24px" viewBox="0 0 24 24" version="1.1">\
//                                            <g stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">\
//                                                <rect x="0" y="0" width="24" height="24"/>\
//                                                <path d="M6,8 L6,20.5 C6,21.3284271 6.67157288,22 7.5,22 L16.5,22 C17.3284271,22 18,21.3284271 18,20.5 L18,8 L6,8 Z" fill="#000000" fill-rule="nonzero"/>\
//                                                <path d="M14,4.5 L14,4 C14,3.44771525 13.5522847,3 13,3 L11,3 C10.4477153,3 10,3.44771525 10,4 L10,4.5 L5.5,4.5 C5.22385763,4.5 5,4.72385763 5,5 L5,5.5 C5,5.77614237 5.22385763,6 5.5,6 L18.5,6 C18.7761424,6 19,5.77614237 19,5.5 L19,5 C19,4.72385763 18.7761424,4.5 18.5,4.5 L14,4.5 Z" fill="#000000" opacity="0.3"/>\
//                                            </g>\
//                                        </svg>\
//                                    </span>\
//	                        </a>\
//	                    ';
//                        }
//                        
//                        if ( row.pagada ) {
//                            btns += '\
//                            <a href="' + HOST_URL + '/bookings/invoice/' + row.id + '" class="btn btn-sm btn-default btn-text-primary btn-hover-primary btn-icon mr-2" title="Descargar factura">\
//                                <span class="svg-icon svg-icon-primary svg-icon-2x"><!--begin::Svg Icon | path:/var/www/preview.keenthemes.com/metronic/releases/2021-05-14-112058/theme/html/demo1/dist/../src/media/svg/icons/Files/DownloadedFile.svg--><svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" width="24px" height="24px" viewBox="0 0 24 24" version="1.1">\
//                                    <g stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">\
//                                        <polygon points="0 0 24 0 24 24 0 24"/>\
//                                        <path d="M5.85714286,2 L13.7364114,2 C14.0910962,2 14.4343066,2.12568431 14.7051108,2.35473959 L19.4686994,6.3839416 C19.8056532,6.66894833 20,7.08787823 20,7.52920201 L20,20.0833333 C20,21.8738751 19.9795521,22 18.1428571,22 L5.85714286,22 C4.02044787,22 4,21.8738751 4,20.0833333 L4,3.91666667 C4,2.12612489 4.02044787,2 5.85714286,2 Z" fill="#000000" fill-rule="nonzero" opacity="0.3"/>\
//                                        <path d="M14.8875071,11.8306874 L12.9310336,11.8306874 L12.9310336,9.82301606 C12.9310336,9.54687369 12.707176,9.32301606 12.4310336,9.32301606 L11.4077349,9.32301606 C11.1315925,9.32301606 10.9077349,9.54687369 10.9077349,9.82301606 L10.9077349,11.8306874 L8.9512614,11.8306874 C8.67511903,11.8306874 8.4512614,12.054545 8.4512614,12.3306874 C8.4512614,12.448999 8.49321518,12.5634776 8.56966458,12.6537723 L11.5377874,16.1594334 C11.7162223,16.3701835 12.0317191,16.3963802 12.2424692,16.2179453 C12.2635563,16.2000915 12.2831273,16.1805206 12.3009811,16.1594334 L15.2691039,12.6537723 C15.4475388,12.4430222 15.4213421,12.1275254 15.210592,11.9490905 C15.1202973,11.8726411 15.0058187,11.8306874 14.8875071,11.8306874 Z" fill="#000000"/>\
//                                    </g>\
//                                </svg><!--end::Svg Icon--></span>\
//                            </a>\
//                            ';
//                        }
//                        
//                        return btns;
//                    },
//                }],
//        });
//
//        $('#kt_datatable_search_status').on('change', function () {
//            datatable.search($(this).val().toLowerCase(), 'Status');
//        });
//
//        $('#kt_datatable_search_type').on('change', function () {
//            datatable.search($(this).val().toLowerCase(), 'Type');
//        });
//
//        $('#kt_datatable_search_status, #kt_datatable_search_type').selectpicker();
//    };
//
//    return {
//        // public functions
//        init: function () {
//            _demo();
//        },
//    };
//}();

var KTAppsUsersListDatatable = function() {

	var initTable1 = function() {
		var table = $('#kt_datatable');

		// begin first table
		table.DataTable({
                        language: {
                            url: "//cdn.datatables.net/plug-ins/1.10.19/i18n/Spanish.json"
                        },                    
			responsive: true,
			ajax: {
                            url: HOST_URL + '/bookings/all',
                            type: 'POST',
                            data: {
                                pagination: {
                                    perpage: 50,
                                },
                            },
                            dataSrc: ""
			},
			columns: [
                            {
                                data: 'fecha',
                                title: 'Fecha',
                                width: 75,
                            },
                            {
                                data: 'hora',
                                title: 'Hora',
                                width: 75,
                            },
                            {
                                data: 'cancha.nombre',
                                title: 'Cancha',
                                width: 100,
                            },
                            {
                                data: 'cancha.descripcion',
                                title: 'Tipo',
                                width: 100,
                            },
                            {
                                data: 'cancha',
                                title: 'Imagen',
                                width: 100,
                            },
                            {
                                data: 'usuario.nombre_completo',
                                title: 'Usuario',
                                width: 150,
                            },
                            {
                                data: 'pagada',
                                title: 'Estado',
                                width: 150,
                            },
                            {
                                data: 'creado',
                                title: 'Creado',
                                width: 90,
                            },
                            {
                                data: 'modificado',
                                title: 'Modificado',
                                width: 90,
                            },
                            {
                                data: 'id',
                                title: 'Acciones',
                                width: 130,
                            },
                        ],
			columnDefs: [
                            {
                                    targets: -1,
                                    orderable: false,
                                    render: function(data, type, row, meta) {

                                        var btns = '';

                                        if ($("#rol").val() === "Administrador") {
                                            btns =
                                                    '\
                                                <a href="' + HOST_URL + '/bookings/edit/' + row.id + '" class="btn btn-sm btn-default btn-text-primary btn-hover-primary btn-icon mr-2" title="Edit details">\
                                                    <span class="svg-icon svg-icon-md">\
                                                        <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" width="24px" height="24px" viewBox="0 0 24 24" version="1.1">\
                                                            <g stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">\
                                                                <rect x="0" y="0" width="24" height="24"/>\
                                                                <path d="M12.2674799,18.2323597 L12.0084872,5.45852451 C12.0004303,5.06114792 12.1504154,4.6768183 12.4255037,4.38993949 L15.0030167,1.70195304 L17.5910752,4.40093695 C17.8599071,4.6812911 18.0095067,5.05499603 18.0083938,5.44341307 L17.9718262,18.2062508 C17.9694575,19.0329966 17.2985816,19.701953 16.4718324,19.701953 L13.7671717,19.701953 C12.9505952,19.701953 12.2840328,19.0487684 12.2674799,18.2323597 Z" fill="#000000" fill-rule="nonzero" transform="translate(14.701953, 10.701953) rotate(-135.000000) translate(-14.701953, -10.701953) "/>\
                                                                <path d="M12.9,2 C13.4522847,2 13.9,2.44771525 13.9,3 C13.9,3.55228475 13.4522847,4 12.9,4 L6,4 C4.8954305,4 4,4.8954305 4,6 L4,18 C4,19.1045695 4.8954305,20 6,20 L18,20 C19.1045695,20 20,19.1045695 20,18 L20,13 C20,12.4477153 20.4477153,12 21,12 C21.5522847,12 22,12.4477153 22,13 L22,18 C22,20.209139 20.209139,22 18,22 L6,22 C3.790861,22 2,20.209139 2,18 L2,6 C2,3.790861 3.790861,2 6,2 L12.9,2 Z" fill="#000000" fill-rule="nonzero" opacity="0.3"/>\
                                                            </g>\
                                                        </svg>\
                                                    </span>\
                                                </a>\
                                                <a href="javascript:;" onclick="deleteUser(' + '\'/bookings\',' + row.id + ')" class="btn btn-sm btn-default btn-text-primary btn-hover-primary btn-icon mr-2" title="Delete">\
                                                    <span class="svg-icon svg-icon-md">\
                                                        <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" width="24px" height="24px" viewBox="0 0 24 24" version="1.1">\
                                                            <g stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">\
                                                                <rect x="0" y="0" width="24" height="24"/>\
                                                                <path d="M6,8 L6,20.5 C6,21.3284271 6.67157288,22 7.5,22 L16.5,22 C17.3284271,22 18,21.3284271 18,20.5 L18,8 L6,8 Z" fill="#000000" fill-rule="nonzero"/>\
                                                                <path d="M14,4.5 L14,4 C14,3.44771525 13.5522847,3 13,3 L11,3 C10.4477153,3 10,3.44771525 10,4 L10,4.5 L5.5,4.5 C5.22385763,4.5 5,4.72385763 5,5 L5,5.5 C5,5.77614237 5.22385763,6 5.5,6 L18.5,6 C18.7761424,6 19,5.77614237 19,5.5 L19,5 C19,4.72385763 18.7761424,4.5 18.5,4.5 L14,4.5 Z" fill="#000000" opacity="0.3"/>\
                                                            </g>\
                                                        </svg>\
                                                    </span>\
                                                </a>\
                                            ';
                                        }

                                        if (row.pagada) {
                                            btns += '\
                                            <a href="' + HOST_URL + '/bookings/invoice/' + row.id + '" class="btn btn-sm btn-default btn-text-primary btn-hover-primary btn-icon mr-2" title="Descargar factura">\
                                                <span class="svg-icon svg-icon-primary svg-icon-2x"><!--begin::Svg Icon | path:/var/www/preview.keenthemes.com/metronic/releases/2021-05-14-112058/theme/html/demo1/dist/../src/media/svg/icons/Files/DownloadedFile.svg--><svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" width="24px" height="24px" viewBox="0 0 24 24" version="1.1">\
                                                    <g stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">\
                                                        <polygon points="0 0 24 0 24 24 0 24"/>\
                                                        <path d="M5.85714286,2 L13.7364114,2 C14.0910962,2 14.4343066,2.12568431 14.7051108,2.35473959 L19.4686994,6.3839416 C19.8056532,6.66894833 20,7.08787823 20,7.52920201 L20,20.0833333 C20,21.8738751 19.9795521,22 18.1428571,22 L5.85714286,22 C4.02044787,22 4,21.8738751 4,20.0833333 L4,3.91666667 C4,2.12612489 4.02044787,2 5.85714286,2 Z" fill="#000000" fill-rule="nonzero" opacity="0.3"/>\
                                                        <path d="M14.8875071,11.8306874 L12.9310336,11.8306874 L12.9310336,9.82301606 C12.9310336,9.54687369 12.707176,9.32301606 12.4310336,9.32301606 L11.4077349,9.32301606 C11.1315925,9.32301606 10.9077349,9.54687369 10.9077349,9.82301606 L10.9077349,11.8306874 L8.9512614,11.8306874 C8.67511903,11.8306874 8.4512614,12.054545 8.4512614,12.3306874 C8.4512614,12.448999 8.49321518,12.5634776 8.56966458,12.6537723 L11.5377874,16.1594334 C11.7162223,16.3701835 12.0317191,16.3963802 12.2424692,16.2179453 C12.2635563,16.2000915 12.2831273,16.1805206 12.3009811,16.1594334 L15.2691039,12.6537723 C15.4475388,12.4430222 15.4213421,12.1275254 15.210592,11.9490905 C15.1202973,11.8726411 15.0058187,11.8306874 14.8875071,11.8306874 Z" fill="#000000"/>\
                                                    </g>\
                                                </svg><!--end::Svg Icon--></span>\
                                            </a>\
                                            ';
                                        }

                                        if (!row.cancelada && !row.pagada) {
                                            btns += '\
                                            <a href="javascript:;" onclick="cancelBooking(' + + row.id + ')" class="btn btn-sm btn-default btn-text-primary btn-hover-primary btn-icon mr-2" title="Cancelar Reservación">\
                                                <span class="svg-icon svg-icon-primary svg-icon-2x"><!--begin::Svg Icon | path:/var/www/preview.keenthemes.com/metronic/releases/2021-05-14-112058/theme/html/demo2/dist/../src/media/svg/icons/Code/Error-circle.svg--><svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" width="24px" height="24px" viewBox="0 0 24 24" version="1.1">\
                                                    <g stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">\
                                                        <rect x="0" y="0" width="24" height="24"/>\
                                                        <circle fill="#000000" opacity="0.3" cx="12" cy="12" r="10"/>\
                                                        <path d="M12.0355339,10.6213203 L14.863961,7.79289322 C15.2544853,7.40236893 15.8876503,7.40236893 16.2781746,7.79289322 C16.6686989,8.18341751 16.6686989,8.81658249 16.2781746,9.20710678 L13.4497475,12.0355339 L16.2781746,14.863961 C16.6686989,15.2544853 16.6686989,15.8876503 16.2781746,16.2781746 C15.8876503,16.6686989 15.2544853,16.6686989 14.863961,16.2781746 L12.0355339,13.4497475 L9.20710678,16.2781746 C8.81658249,16.6686989 8.18341751,16.6686989 7.79289322,16.2781746 C7.40236893,15.8876503 7.40236893,15.2544853 7.79289322,14.863961 L10.6213203,12.0355339 L7.79289322,9.20710678 C7.40236893,8.81658249 7.40236893,8.18341751 7.79289322,7.79289322 C8.18341751,7.40236893 8.81658249,7.40236893 9.20710678,7.79289322 L12.0355339,10.6213203 Z" fill="#000000"/>\
                                                    </g>\
                                                </svg><!--end::Svg Icon--></span>\
                                            </a>\
                                            ';
                                        }

                                        return btns;
                                    },
                            },
                            {
                                targets: -2,
                                render: function(data, type, full, meta) {
                                    let date = moment(data.toLocaleString());
                                    return date.format("DD-MM-YYYY HH:mm:ss")
                                },
                            },
                            {
                                targets: -3,
                                render: function(data, type, full, meta) {
                                    let date = moment(data.toLocaleString());
                                    return date.format("DD-MM-YYYY HH:mm:ss")
                                },
                            },
                            {
                                targets: -4,
                                render: function(row, type, data, meta) {
                                    if ( !data.cancelada ) {
                                        var status = {
                                            false: {'title': 'Pendiente', 'class': ' label-light-warning'},
                                            true: {'title': 'Pagada', 'class': ' label-light-success'},
                                        };
                                        return '<span class="label label-lg font-weight-bold ' + status[row].class + ' label-inline">' + status[row].title + '</span>';
                                    }
                                    return '<span class="label label-lg font-weight-bold label-light-danger label-inline">Cancelada</span>';
                                },
                            },
                            {
                                targets: 0,
                                render: function(row, type, full, meta) {
                                    let date = moment(row.toLocaleString());
                                    return date.format("DD-MM-YYYY")
                                },
                            },
                            {
                                targets: 4,
                                render: function(row, type, full, meta) {
                                    return row.imagen ? "<a href='" + row.path + "' data-fancybox><img class='img-fluid' src='" + row.path + "'></a>" : "";
                                },
                            },
                    ],
		});
	};

	return {

		//main function to initiate the module
		init: function() {
			initTable1();
		},

	};

}();

jQuery(document).ready(function () {
    KTAppsUsersListDatatable.init();
});
