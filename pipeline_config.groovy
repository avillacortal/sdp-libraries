merge                 = true
allow_scm_jenkinsfile = false

libraries{
    merge = true
    sdp{
        images{
            registry    = "http://docker.pkg.github.com/boozallen/sdp-images"
            repository  = "boozallen/sdp-images"
            cred        = "github"
            docker_args = "-v /tmp:/tmp"
        }
    }
    
   
    sonarqube{
        merge=true
        installation_name ='SonarQubeServer'
        credential_id     ='sonarqube'
    }
    nexus{
        credential_id   = "nexus"
        nexusVersion    = "nexus3"
        protocol        = "http"
        nexusUrl        = "10.226.12.45:8081"
    }
    
}

stages{
    continuous_integration{
        install
        unit_test
        build
        static_code_analysis
        //scan_artifact
    }
}

keywords{
    master = '^[Mm]aster$'
    develop = '^[Dd]evelop(ment|er|)$'
    hotfix = '^[Hh]ot[Ff]ix/.*'
    release = '^[Rr]elease.*'
    feature = '^[Ff]eature/.*'
}

application_environments{
    merge = true
    dev{
        merge = true
        name = 'develop'
        
    }

    test{
        merge = true
        name = 'test'
      
    }

    prod{
        merge = true
        name = 'production'
       
    }
}