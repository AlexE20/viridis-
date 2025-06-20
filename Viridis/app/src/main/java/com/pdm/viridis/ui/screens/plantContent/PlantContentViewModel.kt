package com.pdm.viridis.ui.screens.plantContent

/*class PlantContentViewModel(
    private val plantRepository: PlantRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel(){

    private val plantId = savedStateHandle.get<String>("plantId") ?: ""

    private val _plant = MutableStateFlow<Plant?>(null)
    val plant : StateFlow<Plant?> get() = _plant

    init {
        viewModelScope.launch {
            plantRepository.getPlantsFlow().collect { plantList ->
                _plant.value = plantList.find { it.id == plantId }
            }
        }
    }

    companion object {
        val Factory : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val app = this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as ViridisApplication
                val savedStateHandle = createSavedStateHandle()
                PlantContentViewModel(
                    app.appProvider.providePlantRepository(),
                    savedStateHandle
                )
            }
        }
    }
}*/