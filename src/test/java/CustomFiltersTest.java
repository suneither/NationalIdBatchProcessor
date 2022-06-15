import org.example.Filtering.CustomFilters;
import org.example.Gender;
import org.example.LtuNatIdModel;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NavigableMap;

import static org.junit.Assert.assertEquals;

public class CustomFiltersTest {

    private List<LtuNatIdModel> getNewList(){
        List<LtuNatIdModel> list = new ArrayList<>();
        LtuNatIdModel idModel = new LtuNatIdModel();
        idModel.setGender(Gender.FEMALE);
        idModel.setId(12345L);
        list.add(idModel);
        idModel = new LtuNatIdModel();
        idModel.setGender(Gender.FEMALE);
        idModel.setId(123445L);
        list.add(idModel);
        idModel = new LtuNatIdModel();
        idModel.setGender(Gender.MALE);
        idModel.setId(123454564L);
        list.add(idModel);
        return list;
    }

    @Test
    public void filterByGender_ShouldBeEqualToProvidedListByMale_IsEqual(){
        List<LtuNatIdModel> list = getNewList();

        List<LtuNatIdModel> filteredList = CustomFilters.filterByGender(list, Gender.MALE);

        list.remove(0);
        list.remove(0);

        assertEquals(list, filteredList);
    }

    @Test
    public void filterByGender_ShouldBeEqualToProvidedListByFemale_IsEqual(){
        List<LtuNatIdModel> list = getNewList();

        List<LtuNatIdModel> filteredList = CustomFilters.filterByGender(list, Gender.FEMALE);

        list.remove(2);

        assertEquals(list, filteredList);
    }
}
