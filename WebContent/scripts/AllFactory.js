WMS.factory("AllFactory", function() {
	//debugger;
	var authorization;

    return {
        getContractor: function () {
            return authorization;
        },
        setContractor: function(value) {
        	authorization = value;
        },
        getWarehouse: function () {
            return authorization;
        },
        setWarehouse: function(value) {
        	authorization = value;
        },
        getMaterial: function () {
            return authorization;
        },
        setMaterial: function(value) {
        	authorization = value;
        },
        getInward: function () {
            return authorization;
        },
        setInward: function(value) {
        	authorization = value;
        },
        getOrder: function () {
            return authorization;
        },
        setOrder: function(value) {
        	authorization = value;
        },
        getGivenOrder: function () {
            return authorization;
        },
        setGivenOrder: function(value) {
        	authorization = value;
        },
        getGivenInward: function () {
            return authorization;
        },
        setGivenInward: function(value) {
        	authorization = value;
        },
        getGivenId: function () {
            return authorization;
        },
        setGivenId: function(value) {
        	authorization = value;
        },getInvoiceId: function () {
            return authorization;
        },
        setInvoiceId: function(value) {
        	authorization = value;
        }
    };

})