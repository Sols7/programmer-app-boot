package ru.itsjava.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.itsjava.domain.Notebook;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@DisplayName("Класс ProgrammerServiceImpl")
public class ProgrammerServiceImplTest {

    @Configuration
    static class MyConfiguration {

        @Bean
        public IOService ioService() {
            IOServiceImpl mockIOService = Mockito.mock(IOServiceImpl.class);
            when(mockIOService.input()).thenReturn("Vitaliy");
            return mockIOService;
        }

        @Bean
        public NotebookService notebookService() {
            NotebookServiceImpl mockNotebookService = mock(NotebookServiceImpl.class);
            when(mockNotebookService.getNotebook()).thenReturn(new Notebook("Asus", "G115AF", 2018));
            return mockNotebookService;
        }

        @Bean
        public ProgrammerService programmerService(NotebookService notebookService, IOService ioService) {
            return new ProgrammerServiceImpl(notebookService, ioService);
        }
    }

    @Autowired
    private ProgrammerService programmerService;

    @DisplayName(" корректный метод Привет-программист")
    @Test
    public void shouldHaveCorrectSayUiToNewProgrammer() {
        programmerService.hiToNewProgrammer();
    }
}
