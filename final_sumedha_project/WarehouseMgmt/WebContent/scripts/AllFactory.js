WMS.factory("AllFactory", function() {
	//debugger;
	var authorization,orgId;

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
        },
        getOrganisationId: function () {
            return orgId;
        },
        setOrganisationId: function(value) {
        	orgId = value;
        },
        getCategory: function () {
            return authorization;
        },
        setCategory: function(value) {
        	authorization = value;
        }
    };

});



 WMS.service('auth', auth);

	  auth.$inject = ['$http', '$window'];
	  function auth ($http, $window) {

		  var saveToken = function (token) {
		      $window.localStorage['authtoken'] = token;
		    };

		    var getToken = function () {
		      return $window.localStorage['authtoken'];
		    };

	    var isLoggedIn = function() {
	    	 debugger;
	         var token = getToken();
	         var payload;

	      if(token){
	        payload = token.split('.')[1];
	        payload = $window.atob(payload);
	        payload = JSON.parse(payload);

	        return payload.exp > Date.now() / 1000;
	      } else {
	        return false;
	      }
	    };

	    var currentUser = function() {
	    	debugger;
	    	 if(isLoggedIn()){
	    	        var token = getToken();
	    	        console.log(token);
	    	        var payload = token.split('.')[1];
	    	        payload = $window.atob(payload);
	    	        payload = JSON.parse(payload);
	        return {
	        	 email : payload.email,
	             name : payload.name,
	             org_name : payload._orgName,
	             org_id : payload._orgId
	        };
	      }
	    };

	    /*register = function(user) {
	      return $http.post('/pFactory/register', user).then(function(response){
	        console.log('register $http return: ' + JSON.stringify(response.data));
	        saveToken(response.data.token);
	      });
	    };

	    forgot = function(email) {
	      return $http.post('/pFactory/forgot' , email).then(function(response){

	      });
	    }

	    login = function(user) {
	      return $http.post('/pFactory/login', user).then(function(response) {
	        saveToken(response.data.token);
	      });
	    };

	    logout = function() {
	      $window.localStorage.removeItem('mean-token');
	    };*/

	    return {
	        currentUser : currentUser,
	        saveToken : saveToken,
	        getToken : getToken,
	        isLoggedIn : isLoggedIn
	        
	      };
	  }

