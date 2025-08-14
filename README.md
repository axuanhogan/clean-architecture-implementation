# Overview
This repository demonstrates a Clean Architecture implementation using Quarkus with Kotlin.

# Architecture
### References
1. Clean Architecture
2. Hexagonal Architecture

### Diagram
```mermaid
graph LR
		
	subgraph External Out
		resource[Resource]
		data-store[(DataStore)]
	end
		
	subgraph External In
		web[Web]
	end
	
	subgraph api[API]
        subgraph adapter[Adapter]
            subgraph adapter-in[In]
                adapter-in-gateway[Gateway]
            end
            subgraph adapter-out[Out]
                adapter-out-service-impl[ServiceImpl]
                adapter-out-repo-impl[RepositoryImpl]
                adapter-out-client[Client]
                adapter-out-jpa-repostory[JpaRepostory]
            end
        end
    end
	
	subgraph core[Core]
		subgraph domain-layer[Domain Layer]
			Entity
		end
		subgraph use-case-layer[Use Case Layer]
            subgraph port-out[Port Out（Interface）]
                port-out-service[Service]
            end
            subgraph use-case[Use Case]
            end
		end
	end
	
	web -- Request --> adapter-in-gateway
	
	adapter-in -- Use --> use-case
	use-case -- Use --> domain-layer
	adapter-out-client -. Inject .-> adapter-out-service-impl
	adapter-out-jpa-repostory -. Inject .-> adapter-out-repo-impl
	adapter-out -. Implement .-> port-out
	port-out -. Inject .-> use-case
	
	adapter-out-repo-impl -- Save or Get --> data-store
	adapter-out-service-impl -- Request --> resource
```
