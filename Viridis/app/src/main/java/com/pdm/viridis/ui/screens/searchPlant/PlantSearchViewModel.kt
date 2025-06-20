package com.pdm.viridis.ui.screens.searchPlant

//import com.example.viridis.data.repository.PlantRepositoryImpl

/*class PlantSearchViewModel(
    private val repository: PlantRepository
): ViewModel() {

    private val _searchText = MutableStateFlow("")
    val searchText : StateFlow<String> = _searchText

    private val _filteredPlants = MutableStateFlow<List<Plant>>(emptyList())
    val filteredPlants : StateFlow<List<Plant>> = _filteredPlants

    private var allPlants : List<Plant> = emptyList()

    private val pageSize = 10
    private var currentPage = 1

    init {
        loadInitialPage()
    }

    private fun loadInitialPage(){
        viewModelScope.launch {
            allPlants = repository.getPlants()
            _filteredPlants.value = allPlants.take(pageSize)
        }
    }

    fun onSearchTextChange(newText : String){
        _searchText.value = newText

        _filteredPlants.value = if (newText.isBlank()) {
            allPlants.take(pageSize)
        } else {
            allPlants.filter { plant ->
                plant.name.contains(newText, ignoreCase = true)
            }
        }
    }

    fun loadNextPage() {
        val nextPlants = allPlants.drop(currentPage * pageSize).take(pageSize)
        if (nextPlants.isNotEmpty()) {
            _filteredPlants.value = _filteredPlants.value + nextPlants
            currentPage++
        }
    }

    companion object {
        val Factory : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val app = this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as ViridisApplication
                PlantSearchViewModel(
                    app.appProvider.providePlantRepository()
                )
            }
        }
    }
}*/