package ru.xrfoxyxr.lab5;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Modality;

public class MainController {
    @FXML
    private TextField swapLeftField;
    @FXML
    private TextField swapRightField;
    @FXML
    private Button swapButton;

    @FXML
    private CheckBox widgetTextFieldCheck;
    @FXML
    private TextField widgetTextField;
    @FXML
    private CheckBox widgetSliderCheck;
    @FXML
    private javafx.scene.control.Slider widgetSlider;
    @FXML
    private CheckBox widgetTextAreaCheck;
    @FXML
    private TextArea widgetTextArea;

    @FXML
    private CheckBox dishPizzaCheck;
    @FXML
    private Spinner<Integer> dishPizzaQty;
    @FXML
    private CheckBox dishSaladCheck;
    @FXML
    private Spinner<Integer> dishSaladQty;
    @FXML
    private CheckBox dishSoupCheck;
    @FXML
    private Spinner<Integer> dishSoupQty;

    @FXML
    private TextField calcDisplay;

    @FXML
    private RadioButton flag1Red;
    @FXML
    private RadioButton flag1Green;
    @FXML
    private RadioButton flag1White;
    @FXML
    private RadioButton flag2Blue;
    @FXML
    private RadioButton flag2Yellow;
    @FXML
    private RadioButton flag2White;
    @FXML
    private RadioButton flag3Red;
    @FXML
    private RadioButton flag3Black;
    @FXML
    private RadioButton flag3Green;
    @FXML
    private Label flagResultLabel;

    private final ToggleGroup flagGroup1 = new ToggleGroup();
    private final ToggleGroup flagGroup2 = new ToggleGroup();
    private final ToggleGroup flagGroup3 = new ToggleGroup();
    private boolean swapToRight = true;
    private Double calcStoredValue = null;
    private String calcPendingOp = null;
    private boolean calcStartNewInput = true;

    @FXML
    private void initialize() {
        setVisibleAndManaged(widgetTextField, widgetTextFieldCheck.isSelected());
        setVisibleAndManaged(widgetSlider, widgetSliderCheck.isSelected());
        setVisibleAndManaged(widgetTextArea, widgetTextAreaCheck.isSelected());

        initSpinner(dishPizzaQty);
        initSpinner(dishSaladQty);
        initSpinner(dishSoupQty);

        updateDishState(dishPizzaCheck, dishPizzaQty);
        updateDishState(dishSaladCheck, dishSaladQty);
        updateDishState(dishSoupCheck, dishSoupQty);

        flag1Red.setToggleGroup(flagGroup1);
        flag1Green.setToggleGroup(flagGroup1);
        flag1White.setToggleGroup(flagGroup1);
        flag2Blue.setToggleGroup(flagGroup2);
        flag2Yellow.setToggleGroup(flagGroup2);
        flag2White.setToggleGroup(flagGroup2);
        flag3Red.setToggleGroup(flagGroup3);
        flag3Black.setToggleGroup(flagGroup3);
        flag3Green.setToggleGroup(flagGroup3);

        calcDisplay.setText("0");
    }

    @FXML
    private void handleSwap(ActionEvent event) {
        if (swapToRight) {
            swapRightField.setText(swapLeftField.getText());
            swapLeftField.clear();
            swapButton.setText("<-");
        } else {
            swapLeftField.setText(swapRightField.getText());
            swapRightField.clear();
            swapButton.setText("->");
        }
        swapToRight = !swapToRight;
    }

    @FXML
    private void handleToggleWidget(ActionEvent event) {
        Object source = event.getSource();
        if (source == widgetTextFieldCheck) {
            setVisibleAndManaged(widgetTextField, widgetTextFieldCheck.isSelected());
        } else if (source == widgetSliderCheck) {
            setVisibleAndManaged(widgetSlider, widgetSliderCheck.isSelected());
        } else if (source == widgetTextAreaCheck) {
            setVisibleAndManaged(widgetTextArea, widgetTextAreaCheck.isSelected());
        }
    }

    @FXML
    private void handleDishToggle(ActionEvent event) {
        Object source = event.getSource();
        if (source == dishPizzaCheck) {
            updateDishState(dishPizzaCheck, dishPizzaQty);
        } else if (source == dishSaladCheck) {
            updateDishState(dishSaladCheck, dishSaladQty);
        } else if (source == dishSoupCheck) {
            updateDishState(dishSoupCheck, dishSoupQty);
        }
    }

    @FXML
    private void handleCheckout(ActionEvent event) {
        List<String> lines = new ArrayList<>();
        double total = 0.0;

        total += appendDishLine(lines, dishPizzaCheck, dishPizzaQty, "Пицца", 350.0);
        total += appendDishLine(lines, dishSaladCheck, dishSaladQty, "Салат", 220.0);
        total += appendDishLine(lines, dishSoupCheck, dishSoupQty, "Суп", 180.0);

        if (lines.isEmpty()) {
            showAlert(Alert.AlertType.INFORMATION, "Заказ", "Выберите хотя бы одно блюдо.");
            return;
        }

        StringBuilder receipt = new StringBuilder("Чек:\n");
        for (String line : lines) {
            receipt.append(line).append('\n');
        }
        receipt.append("Итого: ").append(String.format("%.2f", total));

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Чек");
        alert.setHeaderText(null);
        alert.setContentText(receipt.toString());
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.showAndWait();
    }

    @FXML
    private void handleCalcInput(ActionEvent event) {
        Button button = (Button) event.getSource();
        String text = button.getText();

        if (calcStartNewInput) {
            calcDisplay.setText(text.equals(".") ? "0." : text);
            calcStartNewInput = false;
            return;
        }

        if (text.equals(".") && calcDisplay.getText().contains(".")) {
            return;
        }

        calcDisplay.setText(calcDisplay.getText() + text);
    }

    @FXML
    private void handleCalcOperator(ActionEvent event) {
        Button button = (Button) event.getSource();
        String op = button.getText();

        Double current = parseCalcDisplay();
        if (current == null) {
            return;
        }

        if (calcPendingOp != null && calcStoredValue != null && !calcStartNewInput) {
            Double result = applyOperation(calcStoredValue, current, calcPendingOp);
            if (result == null) {
                return;
            }
            calcStoredValue = result;
            calcDisplay.setText(formatNumber(result));
        } else if (calcStoredValue == null) {
            calcStoredValue = current;
        }

        calcPendingOp = op;
        calcStartNewInput = true;
    }

    @FXML
    private void handleCalcEquals(ActionEvent event) {
        Double current = parseCalcDisplay();
        if (current == null) {
            return;
        }
        if (calcPendingOp == null || calcStoredValue == null) {
            return;
        }

        Double result = applyOperation(calcStoredValue, current, calcPendingOp);
        if (result == null) {
            return;
        }

        calcDisplay.setText(formatNumber(result));
        calcStoredValue = null;
        calcPendingOp = null;
        calcStartNewInput = true;
    }

    @FXML
    private void handleCalcClear(ActionEvent event) {
        calcDisplay.setText("0");
        calcStoredValue = null;
        calcPendingOp = null;
        calcStartNewInput = true;
    }

    @FXML
    private void handleCalcSign(ActionEvent event) {
        Double current = parseCalcDisplay();
        if (current == null) {
            return;
        }
        calcDisplay.setText(formatNumber(-current));
    }

    @FXML
    private void handleDrawFlag(ActionEvent event) {
        String first = getToggleText(flagGroup1);
        String second = getToggleText(flagGroup2);
        String third = getToggleText(flagGroup3);

        if (first == null || second == null || third == null) {
            showAlert(Alert.AlertType.WARNING, "Флаг", "Выберите цвет для каждой полосы.");
            return;
        }

        flagResultLabel.setText(first + ", " + second + ", " + third);
    }

    private void initSpinner(Spinner<Integer> spinner) {
        spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 1));
        spinner.setEditable(true);
    }

    private void updateDishState(CheckBox checkBox, Spinner<Integer> spinner) {
        boolean selected = checkBox.isSelected();
        spinner.setDisable(!selected);
        if (selected && spinner.getValue() == null) {
            spinner.getValueFactory().setValue(1);
        }
    }

    private double appendDishLine(List<String> lines, CheckBox checkBox, Spinner<Integer> spinner, String name, double price) {
        if (!checkBox.isSelected()) {
            return 0.0;
        }
        int qty = spinner.getValue() == null ? 1 : spinner.getValue();
        double total = qty * price;
        lines.add(name + " x " + qty + " = " + String.format("%.2f", total));
        return total;
    }

    private Double parseCalcDisplay() {
        try {
            return Double.parseDouble(calcDisplay.getText());
        } catch (NumberFormatException ex) {
            showAlert(Alert.AlertType.ERROR, "Калькулятор", "Некорректное число.");
            return null;
        }
    }

    private Double applyOperation(double left, double right, String op) {
        return switch (op) {
            case "+" -> left + right;
            case "-" -> left - right;
            case "*" -> left * right;
            case "/" -> {
                if (right == 0.0) {
                    showAlert(Alert.AlertType.ERROR, "Калькулятор", "Деление на 0 невозможно.");
                    yield null;
                }
                yield left / right;
            }
            default -> left;
        };
    }

    private String formatNumber(double value) {
        if (value == (long) value) {
            return String.format("%d", (long) value);
        }
        return String.format("%s", value);
    }

    private String getToggleText(ToggleGroup group) {
        Toggle toggle = group.getSelectedToggle();
        if (toggle == null) {
            return null;
        }
        return ((RadioButton) toggle).getText();
    }

    private void setVisibleAndManaged(Node node, boolean visible) {
        node.setVisible(visible);
        node.setManaged(visible);
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.showAndWait();
    }
}
