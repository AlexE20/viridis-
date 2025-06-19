package com.pdm.viridis.ui.screens.addedPlantDetail


/*
class AddedPlantDetailViewModel(
    private val plantRepository: PlantRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val plantId = savedStateHandle.get<String>("plantId") ?: ""

    private val _plant = MutableStateFlow<Plant?>(null)
    val plant: StateFlow<Plant?> get() = _plant

    init {
        viewModelScope.launch {
            plantRepository.getPlantsFlow().collect { plantList ->
                _plant.value = plantList.find { it.id == plantId }
            }
        }
    }

//    fun deletePlant() {
//        viewModelScope.launch {
//            _plant.value?.let {
//                plantRepository.deletePlant(it)
//                _plant.value = null
//            }
//        }
//    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val aplication = this[APPLICATION_KEY] as ViridisApplication
                val savedStateHandle = createSavedStateHandle()
                AddedPlantDetailViewModel(
                    aplication.appProvider.providePlantRepository(),
                    savedStateHandle
                )
            }
        }
    }
}
*/

