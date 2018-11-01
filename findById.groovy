import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "Should return user by id"
    
    request{
        method 'GET'
        url('/users/44')
    }

    response{
       status 200
        body([
               "userid":44,
               "firstName": "nithii",
       		   "lastName": "nithi",
               "email": "nith@gmail.com"
        ])
        headers {
            contentType(applicationJson())
        }
    }
}
