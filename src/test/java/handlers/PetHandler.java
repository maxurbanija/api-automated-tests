package handlers;

import lombok.Data;
import models.response.pet.FindByStatusResponse;
import models.response.pet.Pet;

import java.util.*;
import java.util.stream.Collectors;

@Data
public class PetHandler {
    private List<Pet> repeatedNames = new ArrayList<>();
    private Set<String> uniqueNames = new HashSet<>();
    Map<String, Long> repeatedNamesAndOcurrences;

    public PetHandler(List<Pet> pets) {
        for (Pet pet : pets) {
            if (!this.uniqueNames.add(pet.getName())) {
                this.repeatedNames.add(pet);
            }
        }
    }

    public int getRepeatedNamesPetCount() {
        return repeatedNames.size();
    }

    public Map<String, Long> getRepeatedNamesAndOcurrences() {
        return this.repeatedNames
                .stream()
                .collect(Collectors.groupingBy(Pet::getName, Collectors.counting()));
    }

    public static HashMap<Long, String> createIdAndNamePetTuple(List<Pet> pets) {
        HashMap<Long, String> petsTuple = new HashMap<>();
        FindByStatusResponse parsedResponse = FindByStatusResponse.builder().pets(pets).build();
        parsedResponse.getPets().stream().parallel().forEach(
                pet -> {
                    petsTuple.put(pet.getId(), pet.getName());
                }
        );
        return petsTuple;
    }
}
